package wdabookstore.bookstoremanager.Mappers;

import org.springframework.stereotype.Component;
import wdabookstore.bookstoremanager.dto.inputs.PublisherInputDTO;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
import wdabookstore.bookstoremanager.entities.PublisherEntity;

@Component
public class PublisherMapper {
    public PublisherEntity mapperInputToEntity(PublisherInputDTO inputDTO){
        PublisherEntity entity = new PublisherEntity();
        entity.setId(inputDTO.getId());
        entity.setName(inputDTO.getName());
        entity.setCity(inputDTO.getCity());

        return entity;
    }
    public PublisherOutputDTO mapperEntityToOutput(PublisherEntity entity){
        PublisherOutputDTO outputDTO = new PublisherOutputDTO();
        outputDTO.setId(entity.getId());
        outputDTO.setName(entity.getName());
        outputDTO.setCity(entity.getCity());

        return outputDTO;
    }

}
