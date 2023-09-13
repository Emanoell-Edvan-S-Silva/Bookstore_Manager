package wdabookstore.bookstoremanager.Mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.inputs.UserInputDTO;
import wdabookstore.bookstoremanager.dto.output.UserOutputDTO;
import wdabookstore.bookstoremanager.entities.UserEntity;

@Component
public class UserMapper {
    public UserOutputDTO mapperEntityToOutput(UserEntity entity){
        UserOutputDTO outputDTO = new UserOutputDTO();
        outputDTO.setId(entity.getId());
        outputDTO.setName(entity.getName());
        outputDTO.setEmail(entity.getEmail());
        outputDTO.setCity(entity.getCity());
        outputDTO.setAddress(entity.getAddress());
        outputDTO.setActive_rentals(entity.getActive_rentals());

        return outputDTO;
    }

    public UserEntity mapperInputToEntityCreate(UserInputDTO inputDTO){
        UserEntity entity = new UserEntity();

        entity.setName(inputDTO.getName());
        entity.setEmail(inputDTO.getEmail());
        entity.setCity(inputDTO.getCity());
        entity.setAddress(inputDTO.getAddress());

        return entity;
    }
    public UserEntity mapperInputToEntityUpdate(UserInputDTO inputDTO){
        UserEntity entity = new UserEntity();
        entity.setId(inputDTO.getId());
        entity.setName(inputDTO.getName());
        entity.setEmail(inputDTO.getEmail());
        entity.setCity(inputDTO.getCity());
        entity.setAddress(inputDTO.getAddress());

        return entity;
    }
}
