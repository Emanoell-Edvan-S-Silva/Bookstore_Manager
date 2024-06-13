package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wdabookstore.bookstoremanager.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByAmountGreaterThan(int amount);

    Optional<BookEntity> findByIdAndDeletedFalse(Long id);

    List<BookEntity> findByDeletedFalse();

    List<BookEntity> findByAmountGreaterThanAndDeletedIsFalse(int amount);

    @Query(value = "SELECT * FROM tb_books WHERE deleted = false ORDER BY total_leased DESC LIMIT 4", nativeQuery = true)
    List<BookEntity> findTop4MostLeasedBooks();

    @Query(value = "SELECT EXISTS (SELECT 1 FROM tb_books WHERE LOWER(name) = LOWER(:name) AND publisher_entity_id = :publisherId AND deleted = false)", nativeQuery = true)
    boolean existsByNameIgnoreCaseAndPublisherEntityId(String name, Long publisherId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM tb_books WHERE LOWER(name) = LOWER(:name) AND publisher_entity_id = :publisherId AND id != :id AND deleted = false)", nativeQuery = true)
    boolean existsByNameIgnoreCaseAndPublisherEntityIdAndIdNot(String name, Long publisherId, Long id);

}
