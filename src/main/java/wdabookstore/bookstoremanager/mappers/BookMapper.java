package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.book.BookResponse;
import wdabookstore.bookstoremanager.dto.publisher.PublisherRelatedAnswer;
import wdabookstore.bookstoremanager.dto.publisher.PublisherResponse;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;

@Component
public class BookMapper {

    public BookResponse mapperEntityToOutput(BookEntity entity){
        BookResponse outputDTO = new BookResponse();
        outputDTO.setId(entity.getId());
        outputDTO.setName(entity.getName());
        outputDTO.setAuthor(entity.getAuthor());
        outputDTO.setAmount(entity.getAmount());
        outputDTO.setLaunch(entity.getLaunch());
        outputDTO.setTotal_leased(entity.getTotal_leased());
        outputDTO.setPublisher(toPublisherOutput(entity.getPublisherEntity()));
        return outputDTO;
    }
    public BookEntity mapperInputToEntityCreate(BookInputCreate bookInputUpdate, PublisherEntity publisherInputDTO){
        BookEntity entity = new BookEntity();
        entity.setName(bookInputUpdate.getName());
        entity.setAuthor(bookInputUpdate.getAuthor());
        entity.setAmount(bookInputUpdate.getAmount());
        entity.setLaunch(bookInputUpdate.getLaunch());
        entity.setPublisherEntity(publisherInputDTO);
        return entity;
    }

    public BookEntity mapperInputToEntityUpdate(BookInputUpdate inputDTO, PublisherEntity publisherInputDTO){
        BookEntity entity = new BookEntity();
        entity.setId(inputDTO.getId());
        entity.setName(inputDTO.getName());
        entity.setAuthor(inputDTO.getAuthor());
        entity.setAmount(inputDTO.getAmount());
        entity.setLaunch(inputDTO.getLaunch());
        entity.setPublisherEntity(publisherInputDTO);
        return entity;
    }

    private PublisherRelatedAnswer toPublisherOutput(PublisherEntity publisherEntity){
        PublisherRelatedAnswer publisher = new PublisherRelatedAnswer();
        publisher.setId(publisherEntity.getId());
        publisher.setName(publisherEntity.getName());
        return publisher;
    }
}
