package wdabookstore.bookstoremanager.services.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.RentEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.RentRepository;
import wdabookstore.bookstoremanager.services.book.BookQueryService;
import wdabookstore.bookstoremanager.services.user.UserQueryService;

import java.util.List;

@Service
public class RentQueryService {
    @Autowired
    BookQueryService bookQueryService;

    @Autowired
    UserQueryService userQueryService;

    @Autowired
    RentRepository rentRepository;

    public List<RentEntity> findAll() {
        return rentRepository.findAll();
    }

    public RentEntity findById(Long id){
        return this.rentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado!"));
    }

    public List<RentEntity> getLateAndOnTimeRents() {
        return rentRepository.findLateAndOnTimeRents();
    }

    public List<RentEntity> getOutstandingRents() {
        return rentRepository.findOutstandingRents();
    }

    public BookEntity findBookById(Long id){
        return bookQueryService.findById(id);
    }

    public UserEntity findUserById(Long id){
        return userQueryService.findById(id);
    }

}
