package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.output.book_outputs.BookResponse;
import wdabookstore.bookstoremanager.dto.output.publisher_outputs.PublisherResponse;
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

    private PublisherResponse toPublisherOutput(PublisherEntity publisherEntity){
        PublisherResponse publisherResponse = new PublisherResponse();
        publisherResponse.setId(publisherEntity.getId());
        publisherResponse.setCity(publisherEntity.getCity());
        publisherResponse.setName(publisherEntity.getName());
        return publisherResponse;
    }
}
