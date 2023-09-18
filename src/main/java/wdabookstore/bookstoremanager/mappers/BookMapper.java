package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.output.BookOutputDTO;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;


@Component
public class BookMapper {

    public BookOutputDTO mapperEntityToOutput(BookEntity entity){
        BookOutputDTO outputDTO = new BookOutputDTO();
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

    private PublisherOutputDTO toPublisherOutput(PublisherEntity publisherEntity){
        PublisherOutputDTO publisherOutputDTO = new PublisherOutputDTO();
        publisherOutputDTO.setId(publisherEntity.getId());
        publisherOutputDTO.setCity(publisherEntity.getCity());
        publisherOutputDTO.setName(publisherEntity.getName());
        return publisherOutputDTO;
    }
}
