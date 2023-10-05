package wdabookstore.bookstoremanager.services.impl.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldExceptions;
import wdabookstore.bookstoremanager.mappers.PublisherMapper;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherCommandService;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherQueryService;

import javax.validation.Valid;

@SuppressWarnings("unused")
@Service
public class PublisherCommandServiceImpl implements PublisherCommandService {
    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherQueryService publisherQueryServices;

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    @Transactional
    public void create(PublisherInputCreate request) {
        if (publisherQueryServices.publisherNameExist(request.getName())){
            throw new ExistingFieldExceptions("Já existe uma editora com esse nome");
        }
        PublisherEntity publisher = publisherMapper.mapperInputToEntityCreate(request);
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional
    public void update(@Valid PublisherInputUpdate request) {
        publisherQueryServices.findById(request.getId());
        PublisherEntity publisher = publisherMapper.mapperInputToEntityUpdate(request);
        boolean existsNameInPublisher = publisherRepository.existsByNameAndIdNotAndDeletedFalse(publisher.getName(), publisher.getId());
        if (existsNameInPublisher){
            throw new ExistingFieldExceptions("Já existe uma editora com esse nome");
        }
        publisherRepository.save(publisher);
        publisherMapper.mapperEntityToOutput(publisher);
    }

    @Override
    public void delete(Long id) {
        boolean publisherValidation = rentalRepository.rentalPublisherValidation(id);
        if (publisherValidation){
            throw new DeleteErrorException("A editora tem livros alugados");
        }
        PublisherEntity publisher = publisherQueryServices.findById(id);
        publisher.setDeleted(true);
        publisherRepository.save(publisher);
    }
}
