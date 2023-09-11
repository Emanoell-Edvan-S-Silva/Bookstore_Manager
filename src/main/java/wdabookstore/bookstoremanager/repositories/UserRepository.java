package wdabookstore.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wdabookstore.bookstoremanager.entities.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long>{
}
