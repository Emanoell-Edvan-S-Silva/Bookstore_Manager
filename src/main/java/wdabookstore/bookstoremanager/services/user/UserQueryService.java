package wdabookstore.bookstoremanager.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.UserRepository;

import java.util.List;

@Service
public class UserQueryService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }
}
