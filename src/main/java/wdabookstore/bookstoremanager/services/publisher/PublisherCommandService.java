package wdabookstore.bookstoremanager.services.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputCreate;
import wdabookstore.bookstoremanager.mappers.PublisherMapper;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.output.publisher_outputs.PublisherResponse;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;

import javax.validation.Valid;

@Service
public class PublisherCommandService {
    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherQueryService publisherQueryServices;

    @Transactional
    public void create(PublisherInputCreate publisherdata) {
        PublisherEntity publisher = publisherMapper.mapperInputToEntityCreate(publisherdata);
        publisherRepository.save(publisher);
        publisherMapper.mapperEntityToOutput(publisher);
    }

    @Transactional
    public void update(@Valid PublisherInputUpdate request) {
        publisherQueryServices.findById(request.getId());
        PublisherEntity publisher = publisherMapper.mapperInputToEntityUpdate(request);
        publisherRepository.save(publisher);
        publisherMapper.mapperEntityToOutput(publisher);
    }

    public void delete(Long id) {
        publisherQueryServices.findById(id);
        try {
            this.publisherRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há livros relacionados");
        }
    }
}
