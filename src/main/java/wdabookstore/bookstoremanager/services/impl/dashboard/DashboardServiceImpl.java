package wdabookstore.bookstoremanager.services.impl.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.repositories.UserRepository;
import wdabookstore.bookstoremanager.services.interfaces.dashboard.DashboardService;
import wdabookstore.bookstoremanager.status.RentalStatus;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<PublisherEntity> findAllPublisher() {
        return publisherRepository.findAll();
    }

    @Override
    public List<RentalEntity> findAllRentals(){ return rentalRepository.findAll(); }

    @Override
    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public List<BookEntity> findTop4MostLeasedBooks() {
        return bookRepository.findTop4MostLeasedBooks();
    }

    @Override
    public UserEntity findUserWithMostRentals() {
        return rentalRepository.findUserWithMostRentals();
    }

    @Override
    public List<RentalEntity> findOnTimeRentals(){
        return rentalRepository.findByStatus(RentalStatus.ON_TIME);
    }

    @Override
    public List<RentalEntity> findLateRentals(){
        return rentalRepository.findByStatus(RentalStatus.LATE_TIME);
    }

    @Override
    public List<RentalEntity> findOutstandingRentals() {
        return rentalRepository.findByStatus(RentalStatus.PENDENT);
    }




}
