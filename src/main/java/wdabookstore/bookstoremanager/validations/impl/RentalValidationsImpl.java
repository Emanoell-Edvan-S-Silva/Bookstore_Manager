package wdabookstore.bookstoremanager.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.exceptions.validation_exceptions.*;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.validations.interfaces.RentalValidations;

import java.time.LocalDate;

@SuppressWarnings("unused")
@Service
public class RentalValidationsImpl implements RentalValidations {
    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public void validateCreateRental(RentalInputCreate rentalInputCreate, BookEntity book, LocalDate datenow) {
        validateAmount(book);
        validateRentalUserAndBook(rentalInputCreate.getUserId(), book.getId());
        validatePrevisionDate(rentalInputCreate.getPrevisiondate(), datenow);
    }

    @Override
    public void validateDeleteRental(Long id) {
        validateDelete(id);
    }
    @Override
    public void validateFinalizeRental(Long id) {
        validateFinalize(id);
    }

    private void validateDelete(Long id){
        if (rentalRepository.OutstandingRentalValidation(id)){
            throw new DeleteErrorException("Não foi possivel excluir pois o Aluguel não foi dado baixa");
        }
    }
    private void validateFinalize(Long id){
        if (!rentalRepository.OutstandingRentalValidation(id)){
            throw new FinalizeErrorException("Não é possivel finalizar um aluguel que não esteja pendente");
        }
    }

    private void validateAmount(BookEntity book) {
        if (book.getAmount() <= 0) {
            throw new BusinessErrorException("O livro selecionado não possui estoque no momento");
        }
    }

    private void validateRentalUserAndBook(Long userId, Long bookId) {
        if (rentalRepository.rentalUserAndBookValidation(userId, bookId)) {
            throw new BusinessErrorException("O usuário já está alugando esse livro");
        }
    }

    private void validatePrevisionDate(LocalDate previsionDate, LocalDate datenow) {
        if (previsionDate.isEqual(datenow) || previsionDate.isBefore(datenow)) {
            throw new InvalidDateException("A data de previsão não pode ser igual ou inferior à data de aluguel");
        }
    }
}
