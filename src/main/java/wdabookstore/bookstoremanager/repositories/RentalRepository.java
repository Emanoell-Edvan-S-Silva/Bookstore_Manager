package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.status.RentalStatus;

import java.util.List;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {

    List<RentalEntity> findByDeletedFalse();

    List<RentalEntity> findByStatus(RentalStatus status);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM tb_rentals WHERE user_entity_id = :userId AND returndate IS NULL AND deleted = false)", nativeQuery = true)
    boolean rentalUserValidation(Long userId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM tb_rentals WHERE book_entity_id = :bookId AND returndate IS NULL AND deleted = false)", nativeQuery = true)
    boolean rentalBookValidation(Long bookId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM tb_rentals WHERE id = :RentalId AND returndate IS NULL AND deleted = false)", nativeQuery = true)
    boolean OutstandingRentalValidation(Long RentalId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM tb_rentals WHERE user_entity_id = :userId AND book_entity_id = :bookId AND status = 'PENDENT' AND deleted = false)", nativeQuery = true)
    boolean rentalUserAndBookValidation(Long userId, Long bookId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM tb_rentals r INNER JOIN tb_books b ON r.book_entity_id = b.id WHERE b.publisher_entity_id = :publisherId AND r.returndate IS NULL AND r.deleted = false)", nativeQuery = true)
    boolean rentalPublisherValidation(Long publisherId);

    @Query(value = "SELECT u.* FROM tb_users u JOIN (SELECT user_entity_id, COUNT(*) AS rental_count FROM tb_rentals GROUP BY user_entity_id ORDER BY rental_count DESC LIMIT 1) AS most_rented ON u.id = most_rented.user_entity_id", nativeQuery = true)
    UserEntity findUserWithMostRentals();

}
