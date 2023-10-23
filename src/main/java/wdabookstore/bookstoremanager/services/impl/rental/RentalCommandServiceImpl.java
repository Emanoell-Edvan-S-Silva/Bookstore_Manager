package wdabookstore.bookstoremanager.services.impl.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldExceptions;
import wdabookstore.bookstoremanager.mappers.RentalMapper;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.rental.RentalCommandService;
import wdabookstore.bookstoremanager.services.interfaces.rental.RentalQueryService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;

@SuppressWarnings("unused")
@Service
public class RentalCommandServiceImpl implements RentalCommandService {
    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RentalMapper rentalMapper;

    @Autowired
    RentalQueryService rentalQueryService;

    LocalDate datenow = LocalDate.now(ZoneId.of("America/Sao_Paulo"));

    @Transactional
    @Override
    public void create(RentalInputCreate rentalInputCreate) {
        boolean amountValidation = bookRepository.existsByIdAndAmountGreaterThan(rentalInputCreate.getBookId(), 0);
        boolean rentalUserAndBookValidation = rentalRepository.rentalUserAndBookValidation(rentalInputCreate.getUserId(), rentalInputCreate.getBookId());
        if (!amountValidation){
            throw new ExistingFieldExceptions("O livro selecionado não possui estoque no momento");
        }
        if (rentalUserAndBookValidation){
            throw new ExistingFieldExceptions("O usuario já está alugando esse livro");
        }
        UserEntity user = rentalQueryService.findUserById(rentalInputCreate.getUserId());
        BookEntity book = rentalQueryService.findBookById(rentalInputCreate.getBookId());
        RentalEntity rent = rentalMapper.mapperInputToEntityCreate(rentalInputCreate, book, user);
        book.setAmount(book.getAmount() - 1);
        book.setTotal_leased(book.getTotal_leased() + 1);
        rentalRepository.save(rent);
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void finalizeRent (@Valid Long id) {
        RentalEntity rent = rentalQueryService.findById(id);
        BookEntity book = rent.getBookEntity();
        book.setAmount(book.getAmount() + 1);
        rent.setReturndate(datenow);
        bookRepository.save(book);
        rentalRepository.save(rent);
    }

    @Override
    public void delete(Long id) {
        boolean OutstandingRentalValidation = rentalRepository.OutstandingRentalValidation(id);
        if (OutstandingRentalValidation){
            throw new DeleteErrorException("Não foi possivel excluir pois o Aluguel não foi dado baixa");
        }
        RentalEntity rent = rentalQueryService.findById(id);
        rent.setDeleted(true);
        rentalRepository.save(rent);
    }

}
