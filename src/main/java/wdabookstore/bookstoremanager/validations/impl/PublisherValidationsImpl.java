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
        validateNameUniquenessCreate(publisherInputCreate.getName());
    }

    @Override
    public void validateUpdate(PublisherInputUpdate publisherInputUpdate) {
        PublisherEntity existingPublisher = publisherQueryService.findById(publisherInputUpdate.getId());
        validateNameUniquenessUpdate(publisherInputUpdate.getName(), existingPublisher.getId());
    }

    @Override
    public void validateDelete(Long publisherId) {
        if (rentalRepository.rentalPublisherValidation(publisherId)) {
            throw new BusinessErrorException("A editora tem livros alugados");
        }
    }

    private void validateNameUniquenessCreate(String name) {
        name = name.trim();
        if (publisherQueryService.publisherNameExists(name)) {
            throw new ExistingFieldException("Já existe uma editora com esse nome");
        }
    }

    private void validateNameUniquenessUpdate(String name, Long publisherId) {
        name = name.trim();
        if (publisherRepository.existsByNameIgnoreCaseAndIdNotAndDeletedFalse(name, publisherId)) {
            throw new ExistingFieldException("Já existe uma editora com esse nome");
        }
    }
}
