package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wdabookstore.bookstoremanager.entities.PublisherEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
    boolean existsByNameAndDeletedFalse(String Name);

    boolean existsByNameAndIdNotAndDeletedFalse(String name, Long publisherId);

    Optional<PublisherEntity> findByIdAndDeletedFalse(Long id);

    List<PublisherEntity> findByDeletedFalse();

}
