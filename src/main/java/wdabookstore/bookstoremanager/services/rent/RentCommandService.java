package wdabookstore.bookstoremanager.services.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.rent.RentFinalizeRent;
import wdabookstore.bookstoremanager.dto.rent.RentInputCreate;
import wdabookstore.bookstoremanager.dto.rent.RentExtendRent;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.RentEntity;
import wdabookstore.bookstoremanager.entities.UserEntity;
import wdabookstore.bookstoremanager.mappers.RentMapper;
import wdabookstore.bookstoremanager.repositories.RentRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class RentCommandService {
    @Autowired
    RentRepository rentRepository;

    @Autowired
    RentMapper rentMapper;

    @Autowired
    RentQueryService rentQueryService;

    @Transactional
    public void create(RentInputCreate rentInputCreate) {
        UserEntity user = rentQueryService.findUserById(rentInputCreate.getUserId());
        BookEntity book = rentQueryService.findBookById(rentInputCreate.getBookId());
        RentEntity rent = rentMapper.mapperInputToEntityCreate(rentInputCreate, book, user);
        rentRepository.save(rent);
    }
    @Transactional
    public void extendRent (@Valid RentExtendRent rentExtendRent) {
        RentEntity rent = rentQueryService.findById(rentExtendRent.getId());
        rent.setForecastdate(rentExtendRent.getForecastdate());
        rentRepository.save(rent);
    }

    @Transactional
    public void finalizeRent (@Valid RentFinalizeRent rentFinalizeRent) {
        RentEntity rent = rentQueryService.findById(rentFinalizeRent.getId());
        rent.setReturndate(rentFinalizeRent.getReturndate());
        rentRepository.save(rent);
    }

    public void delete(Long id) {
        rentQueryService.findById(id);
        try {
            this.rentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir o aluguel");
        }
    }

    public void updateRentStatus() {
        List<RentEntity> rents = rentQueryService.findAll();

        for (RentEntity rent : rents) {
            if (rent.getReturndate() == null) {
                rent.setStatus("Pendente");
            } else if (rent.getForecastdate().compareTo(rent.getReturndate()) > 0) {
                rent.setStatus("Atrasado");
            } else {
                rent.setStatus("No prazo");
            }
            rentRepository.save(rent);
        }
    }
}
