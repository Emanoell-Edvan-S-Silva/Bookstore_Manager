package wdabookstore.bookstoremanager.services.interfaces.user;

import wdabookstore.bookstoremanager.entities.UserEntity;

import java.util.List;

public interface UserQueryService {
    List<UserEntity> findAllNotDeleted();

    UserEntity findById(Long id);
}
