package wdabookstore.bookstoremanager.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.services.publisher.PublisherQueryService;

import java.util.List;

@Service
public class BookQueryService {
    @Autowired
    private PublisherQueryService publisherQueryService;
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public BookEntity findById(Long id){
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
    }

    public PublisherEntity findPublisher(Long id){
        return publisherQueryService.findById(id);
    }

    public List<BookEntity> findBooksAvailable() {
        return bookRepository.findBooksAvailable();
    }
}
