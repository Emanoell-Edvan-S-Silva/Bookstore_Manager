package wdabookstore.bookstoremanager.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.UserRepository;

import javax.validation.Valid;

@Service
public class UserCommandService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserQueryService userQueryService;

    @Transactional
    public void create(UserInputCreate userInputUpdate) {
        UserEntity user = userMapper.mapperInputToEntityCreate(userInputUpdate);
        userRepository.save(user);
        userMapper.mapperEntityToOutput(user);
    }

    @Transactional
    public void update(@Valid UserInputUpdate userInputUpdate) {
        userQueryService.findById(userInputUpdate.getId());
        UserEntity user = userMapper.mapperInputToEntityUpdate(userInputUpdate);
        userRepository.save(user);
        userMapper.mapperEntityToOutput(user);
    }

    public void delete(Long id) {
        userQueryService.findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há Aluguéis relacionados");
        }
    }
}
