package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wdabookstore.bookstoremanager.entities.BooksEntity;

@Repository
public interface BookRepository extends JpaRepository<BooksEntity, Long> {
}
