package wdabookstore.bookstoremanager.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.BusinessErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldException;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherQueryService;
import wdabookstore.bookstoremanager.validations.interfaces.PublisherValidations;

@SuppressWarnings("unused")
@Service
public class PublisherValidationsImpl implements PublisherValidations {
    @Autowired
    private PublisherQueryService publisherQueryService;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public void validateCreate(PublisherInputCreate publisherInputCreate) {
        validateNameUniqueness(publisherInputCreate.getName());
    }

    @Override
    public void validateUpdate(PublisherInputUpdate publisherInputUpdate) {
        PublisherEntity existingPublisher = publisherQueryService.findById(publisherInputUpdate.getId());
        validateNameUniqueness(publisherInputUpdate.getName(), existingPublisher.getId());
    }

    @Override
    public void validateDelete(Long publisherId) {
        if (rentalRepository.rentalPublisherValidation(publisherId)) {
            throw new BusinessErrorException("A editora tem livros alugados");
        }
    }

    private void validateNameUniqueness(String name) {
        if (publisherQueryService.publisherNameExist(name)) {
            throw new ExistingFieldException("Já existe uma editora com esse nome");
        }
    }

    private void validateNameUniqueness(String name, Long publisherId) {
        if (publisherRepository.existsByNameAndIdNotAndDeletedFalse(name, publisherId)) {
            throw new ExistingFieldException("Já existe uma editora com esse nome");
        }
    }
}
