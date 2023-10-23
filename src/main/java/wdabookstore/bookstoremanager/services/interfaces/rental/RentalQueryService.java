package wdabookstore.bookstoremanager.services.interfaces.rental;

import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;

import java.util.List;

public interface RentalQueryService {

    List<RentalEntity> findAllRentals();

    RentalEntity findById(Long id);

    void updateRentStatus();

    BookEntity findBookById(Long id);

    UserEntity findUserById(Long id);
}
