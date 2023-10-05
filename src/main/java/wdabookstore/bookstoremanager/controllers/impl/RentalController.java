package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdabookstore.bookstoremanager.controllers.interfaces.RentalControllerDocs;
import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
import wdabookstore.bookstoremanager.dto.rental.RentalExtendRent;
import wdabookstore.bookstoremanager.dto.rental.RentalResponse;
import wdabookstore.bookstoremanager.entities.RentalEntity;
import wdabookstore.bookstoremanager.mappers.RentalMapper;
import wdabookstore.bookstoremanager.services.interfaces.rental.RentalCommandService;
import wdabookstore.bookstoremanager.services.interfaces.rental.RentalQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/Rentals")
public class RentalController implements RentalControllerDocs {
    @Autowired
    RentalQueryService rentalQueryService;

    @Autowired
    RentalCommandService rentalCommandService;

    @Autowired
    RentalMapper rentalMapper;

    @Override
    public ResponseEntity<List<RentalResponse>> getOutstandingRentals() {
        rentalQueryService.updateRentStatus();
        List<RentalEntity> rentEntities = rentalQueryService.getOutstandingRentals();
        List<RentalResponse> rent = rentEntities.stream()
                .map(rentalMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rent);
    }

    @Override
    public ResponseEntity<List<RentalResponse>> getHandedOut() {
        rentalQueryService.updateRentStatus();
        List<RentalEntity> rentEntities = rentalQueryService.findHandedOutRentals();
        List<RentalResponse> rent = rentEntities.stream()
                .map(rentalMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rent);
    }

    @Override
    public ResponseEntity<RentalResponse> findById(@PathVariable Long id){
        RentalResponse rent = rentalMapper
                .mapperEntityToOutput(rentalQueryService.findById(id));
        return ResponseEntity.ok().body(rent);
    }

    @Override
    public ResponseEntity<Void> create(@Valid @RequestBody RentalInputCreate rent){
        rentalCommandService.create(rent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> extendRent(@Valid @RequestBody RentalExtendRent rent){
        rentalCommandService.extendRent(rent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> finalizeRent(@PathVariable Long id){
        rentalCommandService.finalizeRent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        rentalCommandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
