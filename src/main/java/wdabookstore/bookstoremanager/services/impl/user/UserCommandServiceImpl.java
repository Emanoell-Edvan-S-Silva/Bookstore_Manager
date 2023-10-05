package wdabookstore.bookstoremanager.services.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldExceptions;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.repositories.UserRepository;
import wdabookstore.bookstoremanager.services.interfaces.user.UserCommandService;
import wdabookstore.bookstoremanager.services.interfaces.user.UserQueryService;

import javax.validation.Valid;

@SuppressWarnings("unused")
@Service
public class UserCommandServiceImpl implements UserCommandService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    @Transactional
    public void create(UserInputCreate userInputUpdate) {
        boolean validationEmail = userRepository.existsByEmail(userInputUpdate.getEmail());
        if (validationEmail){
            throw new ExistingFieldExceptions("Email já existe");
        }
        UserEntity user = userMapper.mapperInputToEntityCreate(userInputUpdate);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(@Valid UserInputUpdate userInputUpdate) {
        userQueryService.findById(userInputUpdate.getId());
        UserEntity user = userMapper.mapperInputToEntityUpdate(userInputUpdate);
        boolean existsNameInPublisher = userRepository.existsByEmailAndIdNotAndDeletedFalse(user.getEmail(), user.getId());
        if (existsNameInPublisher){
            throw new ExistingFieldExceptions("Já existe um usuário com esse e-mail");
        }
        userRepository.save(user);
        userMapper.mapperEntityToOutput(user);
    }

    @Override
    public void delete(Long id) {
        boolean userValidation = rentalRepository.rentalUserValidation(id);
        if (userValidation){
            throw new DeleteErrorException("Este Usuário tem um aluguel pendente");
        }
        UserEntity user = userQueryService.findById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}
