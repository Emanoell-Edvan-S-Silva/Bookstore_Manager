package wdabookstore.bookstoremanager.services.interfaces.user;

import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;

import javax.validation.Valid;

public interface UserCommandService {
    void create(UserInputCreate userInputUpdate);

    void update(@Valid UserInputUpdate userInputUpdate);

    void delete(Long id);
}
