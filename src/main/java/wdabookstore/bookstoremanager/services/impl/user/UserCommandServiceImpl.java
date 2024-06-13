package wdabookstore.bookstoremanager.services.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.repositories.UserRepository;
import wdabookstore.bookstoremanager.services.interfaces.user.UserCommandService;
import wdabookstore.bookstoremanager.services.interfaces.user.UserQueryService;
import wdabookstore.bookstoremanager.validations.interfaces.UserValidations;

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

    @Autowired
    private UserValidations userValidations;

    @Override
    @Transactional
    public void create(UserInputCreate userInputUpdate) {
        userValidations.validateCreate(userInputUpdate);
        UserEntity user = userMapper.mapperInputToEntityCreate(userInputUpdate);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(@Valid UserInputUpdate userInputUpdate) {
        userQueryService.findById(userInputUpdate.getId());
        userValidations.validateUpdate(userInputUpdate);
        UserEntity user = userMapper.mapperInputToEntityUpdate(userInputUpdate);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userValidations.validateDelete(id);
        UserEntity user = userQueryService.findById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}
