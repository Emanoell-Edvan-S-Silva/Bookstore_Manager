package wdabookstore.bookstoremanager.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.BusinessErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.DeleteErrorException;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.ExistingFieldException;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.book.BookQueryService;
import wdabookstore.bookstoremanager.validations.interfaces.BookValidations;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class BookValidationsImpl implements BookValidations {
    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void validateCreate(BookInputCreate bookInputCreate) {
        PublisherEntity publisher = bookQueryService.findPublisher(bookInputCreate.getPublisherId());
        validateNameUniqueness(bookInputCreate.getName(), bookInputCreate.getPublisherId());
    }

    @Override
    public void validateUpdate(BookInputUpdate bookInputUpdate) {
        PublisherEntity publisher = bookQueryService.findPublisher(bookInputUpdate.getPublisherId());
        validateNameUniqueness(bookInputUpdate.getName(), bookInputUpdate.getPublisherId(), bookInputUpdate.getId());
        validateAmountChange(bookInputUpdate.getId(), bookInputUpdate.getAmount());
        validateBookExistence(bookInputUpdate.getId());
    }

    @Override
    public void validateDelete(Long bookId) {
        if (rentalRepository.rentalBookValidation(bookId)) {
            throw new DeleteErrorException("Este Livro possui um aluguel pendente");
        }
    }

    private void validateNameUniqueness(String name, Long publisherId) {
        if (bookRepository.existsByNameAndPublisherEntityId(name, publisherId)) {
            PublisherEntity publisher = bookQueryService.findPublisher(publisherId);
            throw new ExistingFieldException("Não foi possível criar, pois " + name + " já existe na editora " + publisher.getName());
        }
    }

    private void validateNameUniqueness(String name, Long publisherId, Long bookId) {
        if (bookRepository.existsByNameAndPublisherEntityIdAndIdNot(name, publisherId, bookId)) {
            PublisherEntity publisher = bookQueryService.findPublisher(publisherId);
            throw new ExistingFieldException("Não foi possível Editar, pois " + name + " já existe na editora " + publisher.getName());
        }
    }

    private void validateAmountChange(Long bookId, int newAmount) {
        BookEntity book = bookQueryService.findById(bookId);
        if (newAmount < book.getAmount()) {
            throw new BusinessErrorException("Estoque não pode ser editado para menos que o original");
        }
    }

    private void validateBookExistence(Long bookId) {
        Optional<BookEntity> bookValidation = bookRepository.findByIdAndDeletedFalse(bookId);
        if (bookValidation.isEmpty()) {
            throw new EntityNotFoundException("O livro editado não existe");
        }
    }
}
