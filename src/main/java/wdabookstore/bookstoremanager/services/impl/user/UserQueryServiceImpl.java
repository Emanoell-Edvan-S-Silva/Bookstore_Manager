package wdabookstore.bookstoremanager.services.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.UserRepository;
import wdabookstore.bookstoremanager.services.interfaces.user.UserQueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAllNotDeleted(){
        return userRepository.findAllByDeletedFalse();
    }


    @Override
    public UserEntity findById(Long id){
        return this.userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
    }
}
