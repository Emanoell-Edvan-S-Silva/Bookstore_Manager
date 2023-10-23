package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.book.BookRelatedAnswer;
import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
import wdabookstore.bookstoremanager.dto.rental.RentalResponse;
import wdabookstore.bookstoremanager.dto.user.UserRelatedAnswer;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.status.RentalStatus;

import java.time.LocalDate;
import java.time.ZoneId;


@Component
public class RentalMapper {
    LocalDate datenow = LocalDate.now(ZoneId.of("America/Sao_Paulo"));

    public RentalResponse mapperEntityToOutput(RentalEntity entity){
        RentalResponse outputDTO = new RentalResponse();
        outputDTO.setId(entity.getId());
        outputDTO.setRentaldate(entity.getRentaldate());
        outputDTO.setPrevisiondate(entity.getPrevisiondate());
        outputDTO.setReturndate(entity.getReturndate());
        outputDTO.setStatus(entity.getStatus());
        outputDTO.setBook(toBookOutput(entity.getBookEntity()));
        outputDTO.setUser(toUserOutput(entity.getUserEntity()));
        return outputDTO;
    }

    public RentalEntity mapperInputToEntityCreate(RentalInputCreate rentalInputCreate, BookEntity bookEntity, UserEntity userEntity){
        RentalEntity entity = new RentalEntity();
        entity.setRentaldate(datenow);
        entity.setPrevisiondate(rentalInputCreate.getPrevisiondate());
        entity.setStatus(RentalStatus.PENDENT);
        entity.setBookEntity(bookEntity);
        entity.setUserEntity(userEntity);
        entity.setDeleted(false);
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
