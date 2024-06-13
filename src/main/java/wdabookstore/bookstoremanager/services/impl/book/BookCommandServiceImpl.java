package wdabookstore.bookstoremanager.services.impl.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.mappers.BookMapper;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.book.BookCommandService;
import wdabookstore.bookstoremanager.services.interfaces.book.BookQueryService;
import wdabookstore.bookstoremanager.validations.interfaces.BookValidations;

import javax.validation.Valid;

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

    @Autowired
    private BookValidations bookValidations;

    @Override
    @Transactional
    public void create(BookInputCreate bookInputCreate) {
        bookValidations.validateCreate(bookInputCreate);
        PublisherEntity publisher = bookQueryService.findPublisher(bookInputCreate.getPublisherId());
        BookEntity book = bookMapper.mapperInputToEntityCreate(bookInputCreate, publisher);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void update(@Valid BookInputUpdate bookInputUpdate) {
        bookValidations.validateUpdate(bookInputUpdate);
        PublisherEntity publisher = bookQueryService.findPublisher(bookInputUpdate.getPublisherId());
        BookEntity bookEntity = bookQueryService.findById(bookInputUpdate.getId());
        BookEntity book = bookMapper.mapperInputToEntityUpdate(bookInputUpdate, publisher);
        book.setTotal_leased(bookEntity.getTotal_leased());
        book.setActive_rentals(bookEntity.getActive_rentals());
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookValidations.validateDelete(id);
        BookEntity book = bookQueryService.findById(id);
        book.setDeleted(true);
        bookRepository.save(book);
    }
}
