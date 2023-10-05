package wdabookstore.bookstoremanager.services.impl.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldExceptions;
import wdabookstore.bookstoremanager.mappers.BookMapper;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.book.BookCommandService;
import wdabookstore.bookstoremanager.services.interfaces.book.BookQueryService;

import javax.validation.Valid;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class BookCommandServiceImpl implements BookCommandService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    @Transactional
    public void create(BookInputCreate bookInputCreate) {
        PublisherEntity publisher = bookQueryService.findPublisher(bookInputCreate.getPublisherId());
        boolean existsByNameVerification = bookRepository.existsByNameAndPublisherEntityId(bookInputCreate.getName(), bookInputCreate.getPublisherId());
        if (existsByNameVerification){
            throw new ExistingFieldExceptions("Não foi possivel criar, pois " + bookInputCreate.getName() + " já existe na editora " + publisher.getName());
        }
        BookEntity Book = bookMapper.mapperInputToEntityCreate(bookInputCreate, publisher);
        bookRepository.save(Book);
    }

    @Override
    @Transactional
    public void update(@Valid BookInputUpdate bookInputUpdate) {
        PublisherEntity publisher = bookQueryService.findPublisher(bookInputUpdate.getPublisherId());
        boolean existsByNameVerification = bookRepository.existsByNameAndPublisherEntityIdAndIdNot(bookInputUpdate.getName(), bookInputUpdate.getPublisherId(), bookInputUpdate.getId());
        boolean amountValidation = bookRepository.existsByIdAndAmountGreaterThan(bookInputUpdate.getId(), bookInputUpdate.getAmount());
        Optional<BookEntity> bookValidation = bookRepository.findByIdAndDeletedFalse(bookInputUpdate.getId());
        if (bookValidation.isEmpty()){
            throw new ExistingFieldExceptions("O livro editado não existe");
        }
        if (amountValidation){
            throw new ExistingFieldExceptions("Estoque não pode ser editado para menos que o original");
        }
        if (existsByNameVerification){
            throw new ExistingFieldExceptions("Não foi possivel Editar, pois " + bookInputUpdate.getName() + " já existe na editora " + publisher.getName());
        }
        BookEntity Book = bookMapper.mapperInputToEntityUpdate(bookInputUpdate, publisher);
        bookRepository.save(Book);
    }

    @Override
    public void delete(Long id) {
        boolean bookValidation = rentalRepository.rentalBookValidation(id);
        if (bookValidation){
            throw new DeleteErrorException("Este Livro possui um aluguel pendente");
        }
        BookEntity book = bookQueryService.findById(id);
        book.setDeleted(true);
        bookRepository.save(book);
    }
}
