package wdabookstore.bookstoremanager.services.impl.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.mappers.PublisherMapper;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherCommandService;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherQueryService;
import wdabookstore.bookstoremanager.validations.interfaces.PublisherValidations;

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

    @Autowired
    private PublisherValidations publisherValidations;

    @Override
    @Transactional
    public void create(PublisherInputCreate request) {
        publisherValidations.validateCreate(request);
        PublisherEntity publisher = publisherMapper.mapperInputToEntityCreate(request);
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional
    public void update(@Valid PublisherInputUpdate request) {
        publisherQueryServices.findById(request.getId());
        publisherValidations.validateUpdate(request);
        PublisherEntity publisher = publisherMapper.mapperInputToEntityUpdate(request);
        publisherRepository.save(publisher);
        publisherMapper.mapperEntityToOutput(publisher);
    }

    @Override
    public void delete(Long id) {
        publisherValidations.validateDelete(id);
        PublisherEntity publisher = publisherQueryServices.findById(id);
        publisher.setDeleted(true);
        publisherRepository.save(publisher);
    }
}
