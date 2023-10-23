package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdabookstore.bookstoremanager.controllers.interfaces.DashboardControllerDocs;
import wdabookstore.bookstoremanager.dto.book.BookResponse;
import wdabookstore.bookstoremanager.dto.publisher.PublisherResponse;
import wdabookstore.bookstoremanager.dto.rental.RentalResponse;
import wdabookstore.bookstoremanager.dto.user.UserResponse;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.mappers.BookMapper;
import wdabookstore.bookstoremanager.mappers.PublisherMapper;
import wdabookstore.bookstoremanager.mappers.RentalMapper;
import wdabookstore.bookstoremanager.mappers.UserMapper;
import wdabookstore.bookstoremanager.services.interfaces.dashboard.DashboardService;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/Dashboard")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class DashboardController implements DashboardControllerDocs {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    PublisherMapper publisherMapper;

    @Autowired
    RentalMapper rentalMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DashboardService dashboardService;

    @Override
    public ResponseEntity<List<BookResponse>> findAllBooks(){
        List<BookEntity> bookEntities = dashboardService.findAllBooks();
        List<BookResponse> book = bookEntities.stream()
                .map(bookMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(book);
    }

    @Override
    public ResponseEntity<List<PublisherResponse>> findAllPublisher(){
        List<PublisherEntity> publishersEntities = dashboardService.findAllPublisher();
        List<PublisherResponse> publishers = publishersEntities.stream()
                .map(publisherMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(publishers);
    }

    @Override
    public ResponseEntity<List<RentalResponse>> findAllRentals(){
        List<RentalEntity> rentalEntities = dashboardService.findAllRentals();
        List<RentalResponse> rental = rentalEntities.stream()
                .map(rentalMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rental);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAllUsers(){
        List<UserEntity> userEntities = dashboardService.findAllUsers();
        List<UserResponse> user = userEntities.stream()
                .map(userMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<List<BookResponse>> findTop4MostLeasedBooks(){
        List<BookEntity> bookEntities = dashboardService.findTop4MostLeasedBooks();
        List<BookResponse> book = bookEntities.stream()
                .map(bookMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(book);
    }

    @Override
    public ResponseEntity<UserResponse> findUserWithMostRentals() {
        UserEntity userEntity = dashboardService.findUserWithMostRentals();
        UserResponse userResponse = userMapper.mapperEntityToOutput(userEntity);
        return ResponseEntity.ok().body(userResponse);
    }

    @Override
    public ResponseEntity<List<RentalResponse>> findOnTimeRentals(){
        List<RentalEntity> rentalEntities = dashboardService.findOnTimeRentals();
        List<RentalResponse> rental = rentalEntities.stream()
                .map(rentalMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rental);
    }

    @Override
    public ResponseEntity<List<RentalResponse>> findLateRentals(){
        List<RentalEntity> rentalEntities = dashboardService.findLateRentals();
        List<RentalResponse> rental = rentalEntities.stream()
                .map(rentalMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rental);
    }

    @Override
    public ResponseEntity<List<RentalResponse>> findOutstandingRentals(){
        List<RentalEntity> rentalEntities = dashboardService.findOutstandingRentals();
        List<RentalResponse> rental = rentalEntities.stream()
                .map(rentalMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rental);
    }
}
