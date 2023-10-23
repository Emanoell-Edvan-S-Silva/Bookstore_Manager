package wdabookstore.bookstoremanager.services.interfaces.rental;

import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;

import javax.validation.Valid;

public interface RentalCommandService {

    void create(RentalInputCreate rentalInputCreate);

    void finalizeRent (@Valid Long id);

    void delete(Long id);
}
