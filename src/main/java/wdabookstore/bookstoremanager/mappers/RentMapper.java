package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputUpdate;
import wdabookstore.bookstoremanager.dto.output.book_outputs.BookResponse;
import wdabookstore.bookstoremanager.dto.output.rent_outputs.RentResponse;
import wdabookstore.bookstoremanager.dto.output.user_outputs.UserResponse;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.RentEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;

import java.util.Date;


@Component
public class RentMapper {
    Date datenow = new Date();

    public RentResponse mapperEntityToOutput(RentEntity entity){
        RentResponse outputDTO = new RentResponse();
        outputDTO.setId(entity.getId());
        outputDTO.setRentdate(entity.getRentdate());
        outputDTO.setForecastdate(entity.getForecastdate());
        outputDTO.setReturndate(entity.getReturndate());
        outputDTO.setStatus(entity.getStatus());
        outputDTO.setBook(toBookOutput(entity.getBookEntity()));
        outputDTO.setUser(toUserOutput(entity.getUserEntity()));
        return outputDTO;
    }

    public RentEntity mapperInputToEntityCreate(RentInputCreate rentInputCreate, BookEntity bookEntity, UserEntity userEntity){
        RentEntity entity = new RentEntity();
        entity.setReturndate(rentInputCreate.getReturndate());
        entity.setRentdate(datenow);
        entity.setForecastdate(rentInputCreate.getForecastdate());
        entity.setBookEntity(bookEntity);
        entity.setUserEntity(userEntity);
        return entity;
    }

    public RentEntity mapperInputToEntityUpdate(RentInputUpdate rentInputUpdate, BookEntity bookEntity, UserEntity userEntity){
        RentEntity entity = new RentEntity();
        entity.setId(rentInputUpdate.getId());
        entity.setReturndate(rentInputUpdate.getReturndate());
        entity.setForecastdate(rentInputUpdate.getForecastdate());
        entity.setBookEntity(bookEntity);
        entity.setUserEntity(userEntity);
        return entity;
    }

    public BookResponse toBookOutput(BookEntity bookEntity){
        BookResponse outputDTO = new BookResponse();
        outputDTO.setId(bookEntity.getId());
        outputDTO.setName(bookEntity.getName());
        return outputDTO;
    }

    public UserResponse toUserOutput(UserEntity entity){
        UserResponse outputDTO = new UserResponse();
        outputDTO.setId(entity.getId());
        outputDTO.setName(entity.getName());
        return outputDTO;
    }
}
