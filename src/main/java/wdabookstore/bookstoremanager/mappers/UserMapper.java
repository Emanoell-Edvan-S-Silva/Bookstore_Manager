package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.user.UserInputCreate;
import wdabookstore.bookstoremanager.dto.user.UserInputUpdate;
import wdabookstore.bookstoremanager.dto.user.UserResponse;
import wdabookstore.bookstoremanager.entities.UserEntity;

@Component
public class UserMapper {
    public UserResponse mapperEntityToOutput(UserEntity entity){
        UserResponse outputDTO = new UserResponse();
        outputDTO.setId(entity.getId());
        outputDTO.setName(entity.getName());
        outputDTO.setEmail(entity.getEmail());
        outputDTO.setCity(entity.getCity());
        outputDTO.setAddress(entity.getAddress());
        return outputDTO;
    }

    public UserEntity mapperInputToEntityCreate(UserInputCreate inputDTO){
        UserEntity entity = new UserEntity();
        entity.setName(inputDTO.getName());
        entity.setEmail(inputDTO.getEmail());
        entity.setCity(inputDTO.getCity());
        entity.setAddress(inputDTO.getAddress());
        entity.setDeleted(false);
        return entity;
    }

    public UserEntity mapperInputToEntityUpdate(UserInputUpdate inputDTO){
        UserEntity entity = new UserEntity();
        entity.setId(inputDTO.getId());
        entity.setName(inputDTO.getName());
        entity.setEmail(inputDTO.getEmail());
        entity.setCity(inputDTO.getCity());
        entity.setAddress(inputDTO.getAddress());
        entity.setDeleted(false);
        return entity;
    }
}
