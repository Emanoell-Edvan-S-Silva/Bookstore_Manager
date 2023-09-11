package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wdabookstore.bookstoremanager.entities.RentsEntity;

@Repository
public interface RentRepository extends JpaRepository<RentsEntity, Long> {
}
