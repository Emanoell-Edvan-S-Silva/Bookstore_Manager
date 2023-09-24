package wdabookstore.bookstoremanager.mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherResponse;
import wdabookstore.bookstoremanager.entities.PublisherEntity;

@Component
public class PublisherMapper {
    public PublisherEntity mapperInputToEntityUpdate(PublisherInputUpdate inputDTO){
        PublisherEntity entity = new PublisherEntity();
        entity.setId(inputDTO.getId());
        entity.setName(inputDTO.getName());
        entity.setCity(inputDTO.getCity());

        return entity;
    }
    public PublisherEntity mapperInputToEntityCreate(PublisherInputCreate inputDTO){
        PublisherEntity entity = new PublisherEntity();
        entity.setName(inputDTO.getName());
        entity.setCity(inputDTO.getCity());

        return entity;
    }

    public PublisherResponse mapperEntityToOutput(PublisherEntity entity){
        PublisherResponse outputDTO = new PublisherResponse();
        outputDTO.setId(entity.getId());
        outputDTO.setName(entity.getName());
        outputDTO.setCity(entity.getCity());

        return outputDTO;
    }

    public PublisherInputUpdate mapperEntityToInput(PublisherEntity entity){
        PublisherInputUpdate inputDTO = new PublisherInputUpdate();
        inputDTO.setId(entity.getId());
        inputDTO.setName(entity.getName());
        inputDTO.setCity(entity.getCity());

        return inputDTO;
    }

}
