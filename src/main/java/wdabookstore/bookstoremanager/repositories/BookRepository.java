package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wdabookstore.bookstoremanager.entities.BookEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query(value = "SELECT * FROM tb_books WHERE amount > 0", nativeQuery = true)
    List<BookEntity> findBooksAvailable();
}
