package wdabookstore.bookstoremanager.services.impl.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.dto.user.UserResponse;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.BookRepository;
import wdabookstore.bookstoremanager.repositories.PublisherRepository;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.repositories.UserRepository;
import wdabookstore.bookstoremanager.services.interfaces.book.BookQueryService;
import wdabookstore.bookstoremanager.services.interfaces.dashboard.DashboardService;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherQueryService;
import wdabookstore.bookstoremanager.services.interfaces.rental.RentalQueryService;
import wdabookstore.bookstoremanager.services.interfaces.user.UserQueryService;

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





}
