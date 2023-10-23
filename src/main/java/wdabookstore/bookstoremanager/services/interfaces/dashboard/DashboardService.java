package wdabookstore.bookstoremanager.services.interfaces.dashboard;

import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;

import java.util.List;

public interface DashboardService {

    List<BookEntity> findAllBooks();

    List<PublisherEntity> findAllPublisher();

    List<RentalEntity> findAllRentals();

    List<UserEntity> findAllUsers();

    List<BookEntity> findTop4MostLeasedBooks();

    UserEntity findUserWithMostRentals();

    List<RentalEntity> findOnTimeRentals();

    List<RentalEntity> findLateRentals();

    List<RentalEntity> findOutstandingRentals();
}
