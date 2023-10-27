package wdabookstore.bookstoremanager.services.impl.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherQueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class PublisherQueryServiceImpl implements PublisherQueryService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<PublisherEntity> findAllNotDeleted() {
        return publisherRepository.findByDeletedFalse();
    }


    @Override
    public PublisherEntity findById(Long id){
        return this.publisherRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Editora não encontrada!"));
    }

    @Override
    public boolean publisherNameExist(String name){
        return this.publisherRepository.existsByNameAndDeletedFalse(name);
    }

}
