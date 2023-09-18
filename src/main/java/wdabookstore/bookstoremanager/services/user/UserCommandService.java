package wdabookstore.bookstoremanager.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.inputs.user_inputs.UserInputCreate;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.inputs.user_inputs.UserInputUpdate;
import wdabookstore.bookstoremanager.dto.output.UserOutputDTO;
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
    public UserOutputDTO create(UserInputCreate userInputUpdate) {
        UserEntity user = userMapper.mapperInputToEntityCreate(userInputUpdate);
        userRepository.save(user);
        return userMapper.mapperEntityToOutput(user);
    }

    @Transactional
    public UserOutputDTO update(@Valid UserInputUpdate userInputUpdate) {
        userQueryService.findById(userInputUpdate.getId());
        UserEntity publisher = userMapper.mapperInputToEntityUpdate(userInputUpdate);
        userRepository.save(publisher);
        return userMapper.mapperEntityToOutput(publisher);
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
