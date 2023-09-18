package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wdabookstore.bookstoremanager.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
