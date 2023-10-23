package wdabookstore.bookstoremanager.services.interfaces.book;

import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;

import java.util.List;

public interface BookQueryService {
    List<BookEntity> findAllNotDeleted();

    List<BookEntity> findAllAvailableBooks();

    BookEntity findById(Long id);

    PublisherEntity findPublisher(Long id);

    List<BookEntity> findBooksAvailable();
}
