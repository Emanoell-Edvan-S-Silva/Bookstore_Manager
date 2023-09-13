package wdabookstore.bookstoremanager.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.Mappers.UserMapper;
import wdabookstore.bookstoremanager.dto.inputs.UserInputDTO;
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
    public UserOutputDTO create(UserInputDTO userInputDTO) {
        UserEntity user = userMapper.mapperInputToEntityCreate(userInputDTO);
        userRepository.save(user);
        return userMapper.mapperEntityToOutput(user);
    }

    @Transactional
    public UserOutputDTO update(@Valid UserInputDTO userInputDTO) {
        userQueryService.findById(userInputDTO.getId());
        UserEntity publisher = userMapper.mapperInputToEntityUpdate(userInputDTO);
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
