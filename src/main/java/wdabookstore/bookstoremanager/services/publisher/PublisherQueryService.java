package wdabookstore.bookstoremanager.services.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;

import java.util.List;

@Service
public class PublisherQueryService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<PublisherEntity> findAll() {
        return publisherRepository.findAll();
    }

    public PublisherEntity findById(Long id){
        return this.publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada!"));
    }

}
