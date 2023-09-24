package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.book.BookRelatedAnswer;
import wdabookstore.bookstoremanager.dto.rent.RentInputCreate;
import wdabookstore.bookstoremanager.dto.rent.RentExtendRent;
import wdabookstore.bookstoremanager.dto.rent.RentResponse;
import wdabookstore.bookstoremanager.dto.user.UserRelatedAnswer;
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

    public RentEntity mapperInputToEntityUpdate(RentExtendRent rentExtendRent){
        RentEntity entity = new RentEntity();
        entity.setId(rentExtendRent.getId());
        entity.setForecastdate(rentExtendRent.getForecastdate());

        return entity;
    }

    public BookRelatedAnswer toBookOutput(BookEntity bookEntity){
        BookRelatedAnswer outputDTO = new BookRelatedAnswer();
        outputDTO.setId(bookEntity.getId());
        outputDTO.setName(bookEntity.getName());
        return outputDTO;
    }

    public UserRelatedAnswer toUserOutput(UserEntity entity){
        UserRelatedAnswer outputDTO = new UserRelatedAnswer();
        outputDTO.setId(entity.getId());
        outputDTO.setName(entity.getName());
        return outputDTO;
    }
}
