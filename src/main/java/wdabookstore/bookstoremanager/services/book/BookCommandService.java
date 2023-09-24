package wdabookstore.bookstoremanager.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.mappers.BookMapper;
import wdabookstore.bookstoremanager.repositories.BookRepository;

import javax.validation.Valid;

@Service
public class BookCommandService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookQueryService bookQueryService;

    @Transactional
    public void create(BookInputCreate bookInputCreate) {
        PublisherEntity publisher = bookQueryService.findPublisher(bookInputCreate.getPublisherId());
        BookEntity Book = bookMapper.mapperInputToEntityCreate(bookInputCreate, publisher);
        bookRepository.save(Book);
    }
    @Transactional
    public void update(@Valid BookInputUpdate request) {
        PublisherEntity publisher = bookQueryService.findPublisher(request.getPublisherId());
        BookEntity Book = bookMapper.mapperInputToEntityUpdate(request, publisher);
        bookRepository.save(Book);
    }

    public void delete(Long id) {
        bookQueryService.findById(id);
        try {
            this.bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há Aluguéis relacionados");
        }
    }


}