package wdabookstore.bookstoremanager.validations.interfaces;

import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
import wdabookstore.bookstoremanager.entities.BookEntity;

import java.time.LocalDate;

public interface RentalValidations {

    void validateCreateRental(RentalInputCreate rentalInputCreate, BookEntity book, LocalDate datenow);

    void validateDeleteRental(Long id);

    void validateFinalizeRental(Long id);
}
