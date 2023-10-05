package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wdabookstore.bookstoremanager.entities.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String Email);

    boolean existsByEmailAndIdNotAndDeletedFalse(String email, Long userId);

    Optional<UserEntity> findByIdAndDeletedFalse(Long id);

    List<UserEntity> findAllByDeletedFalse();
}
