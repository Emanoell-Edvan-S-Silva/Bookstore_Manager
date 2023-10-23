package wdabookstore.bookstoremanager.services.impl.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.services.interfaces.book.BookQueryService;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherQueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class BookQueryServiceImpl implements BookQueryService {
    @Autowired
    private PublisherQueryService publisherQueryService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookEntity> findAllNotDeleted() {
        return bookRepository.findAllByDeletedFalse();
    }

    @Override
    public List<BookEntity> findAllAvailableBooks() {
        return bookRepository.findByAmountGreaterThanAndDeletedIsFalse(0);
    }

    @Override
    public BookEntity findById(Long id){
        return this.bookRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));
    }

    @Override
    public PublisherEntity findPublisher(Long id){
        return publisherQueryService.findById(id);
    }

    @Override
    public List<BookEntity> findBooksAvailable() {
        return bookRepository.findByAmountGreaterThan(0);
    }
}
