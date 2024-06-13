package wdabookstore.bookstoremanager.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldException;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.repositories.UserRepository;
import wdabookstore.bookstoremanager.services.interfaces.user.UserQueryService;
import wdabookstore.bookstoremanager.validations.interfaces.UserValidations;

@SuppressWarnings("unused")
@Service
public class UserValidationsImpl implements UserValidations {
    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public void validateCreate(UserInputCreate userInputUpdate) {
        validateEmailExistence(userInputUpdate.getEmail());
    }

    @Override
    public void validateUpdate(UserInputUpdate userInputUpdate) {
        UserEntity existingUser = userQueryService.findById(userInputUpdate.getId());
        validateEmailUniqueness(userInputUpdate.getEmail(), existingUser.getId());
    }

    @Override
    public void validateDelete(Long userId) {
        if (rentalRepository.rentalUserValidation(userId)) {
            throw new DeleteErrorException("Este Usuário tem um aluguel pendente");
        }
    }

    private void validateEmailExistence(String email) {
        email = email.trim();
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new ExistingFieldException("Email já existe");
        }
    }

    private void validateEmailUniqueness(String email, Long userId) {
        email = email.trim();
        if (userRepository.existsByEmailIgnoreCaseAndIdNotAndDeletedFalse(email, userId)) {
            throw new ExistingFieldException("Já existe um usuário com esse e-mail");
        }
    }

}
