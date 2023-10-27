package wdabookstore.bookstoremanager.validations.interfaces;

import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;

public interface UserValidations {
    void validateCreate(UserInputCreate userInputUpdate);

    void validateUpdate(UserInputUpdate userInputUpdate);

    void validateDelete(Long userId);
}
