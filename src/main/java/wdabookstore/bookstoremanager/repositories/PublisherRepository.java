package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wdabookstore.bookstoremanager.entities.PublisherEntity;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
}
