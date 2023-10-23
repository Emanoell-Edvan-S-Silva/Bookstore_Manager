package wdabookstore.bookstoremanager.services.impl.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.repositories.RentalRepository;
import wdabookstore.bookstoremanager.services.interfaces.book.BookQueryService;
import wdabookstore.bookstoremanager.services.interfaces.rental.RentalQueryService;
import wdabookstore.bookstoremanager.services.interfaces.user.UserQueryService;
import wdabookstore.bookstoremanager.status.RentalStatus;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class RentalQueryServiceImpl implements RentalQueryService {
    @Autowired
    BookQueryService bookQueryService;

    @Autowired
    UserQueryService userQueryService;

    @Autowired
    RentalRepository rentalRepository;

    @Override
    public List<RentalEntity> findAllRentals() {
        return rentalRepository.findByDeletedFalse();
    }


    @Override
    public RentalEntity findById(Long id){
        return this.rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluguel não encontrado!"));
    }

    @Override
    public void updateRentStatus() {
        List<RentalEntity> rents = rentalRepository.findAll();

        for (RentalEntity rent : rents) {
            if (rent.getReturndate() == null) {
                rent.setStatus(RentalStatus.PENDENT);
            } else if (rent.getPrevisiondate().isAfter(rent.getReturndate())) {
                rent.setStatus(RentalStatus.ON_TIME);
            } else {
                rent.setStatus(RentalStatus.LATE_TIME);
            }
            rentalRepository.save(rent);
        }
    }

    @Override
    public BookEntity findBookById(Long id){
        return bookQueryService.findById(id);
    }

    @Override
    public UserEntity findUserById(Long id){
        return userQueryService.findById(id);
    }
}
