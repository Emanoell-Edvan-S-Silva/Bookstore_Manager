package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wdabookstore.bookstoremanager.entities.RentEntity;

public interface RentRepository extends JpaRepository<RentEntity, Long> {
}
