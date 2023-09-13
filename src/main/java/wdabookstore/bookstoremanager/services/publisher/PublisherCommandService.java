package wdabookstore.bookstoremanager.services.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.Mappers.PublisherMapper;
import wdabookstore.bookstoremanager.dto.inputs.PublisherInputDTO;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
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
    public PublisherOutputDTO create(PublisherInputDTO publisherdata) {
        PublisherEntity publisher = publisherMapper.mapperInputToEntity(publisherdata);
        publisherRepository.save(publisher);
        return publisherMapper.mapperEntityToOutput(publisher);
    }

    @Transactional
    public PublisherOutputDTO update(@Valid PublisherInputDTO request) {
        publisherQueryServices.findById(request.getId());
        PublisherEntity publisher = publisherMapper.mapperInputToEntity(request);
        publisherRepository.save(publisher);
        return publisherMapper.mapperEntityToOutput(publisher);
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
