package wdabookstore.bookstoremanager.services.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputUpdate;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.entities.RentEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.mappers.RentMapper;
import wdabookstore.bookstoremanager.repositories.RentRepository;

import javax.validation.Valid;

@Service
public class RentCommandService {
    @Autowired
    RentRepository rentRepository;

    @Autowired
    RentMapper rentMapper;

    @Autowired
    RentQueryService rentQueryService;

    @Transactional
    public void create(RentInputCreate rentInputCreate) {
        UserEntity user = rentQueryService.findUserById(rentInputCreate.getUserId());
        BookEntity book = rentQueryService.findBookById(rentInputCreate.getBookId());
        RentEntity rent = rentMapper.mapperInputToEntityCreate(rentInputCreate, book, user);
        rentRepository.save(rent);
    }
    @Transactional
    public void update(@Valid RentInputUpdate rentInputUpdate) {
        UserEntity user = rentQueryService.findUserById(rentInputUpdate.getUserId());
        BookEntity book = rentQueryService.findBookById(rentInputUpdate.getBookId());
        RentEntity rent = rentMapper.mapperInputToEntityUpdate(rentInputUpdate, book, user);
        rentRepository.save(rent);
    }

    public void delete(Long id) {
        rentQueryService.findById(id);
        try {
            this.rentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir o aluguel");
        }
    }
}
