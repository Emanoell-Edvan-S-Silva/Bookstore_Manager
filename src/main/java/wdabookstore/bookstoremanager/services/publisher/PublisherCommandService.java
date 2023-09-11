package wdabookstore.bookstoremanager.services.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.inputs.PublisherInput;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;

import javax.validation.Valid;

@Service
public class PublisherCommandService {
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private PublisherQueryService publisherQueryServices;

    @Transactional
    public void createPublisher(PublisherInput publisherObj) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setName(publisherObj.getName());
        publisher.setCity(publisherObj.getCity());
        publisherRepository.save(publisher);
    }

    @Transactional
    public void updatePublisher(@Valid PublisherInput request) {
        PublisherEntity publisher = publisherQueryServices.findByIdPublisher(request.getId());
        publisher.setName(request.getName());
        publisher.setCity(request.getCity());
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherQueryServices.findByIdPublisher(id);
        try {
            this.publisherRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há livros relacionados");
        }
    }
}
