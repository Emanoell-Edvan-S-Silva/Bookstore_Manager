package wdabookstore.bookstoremanager.controllers.impl;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.controllers.interfaces.RentalControllerDocs;
import wdabookstore.bookstoremanager.dto.rental.RentalInputCreate;
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
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class RentalController implements RentalControllerDocs {
    @Autowired
    RentalQueryService rentalQueryService;

    @Autowired
    RentalCommandService rentalCommandService;

    @Autowired
    RentalMapper rentalMapper;


    @Override
    public ResponseEntity<List<RentalResponse>> findAllRentals() {
        rentalQueryService.updateRentStatus();
        List<RentalEntity> rentEntities = rentalQueryService.findAllRentals();
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
    public ResponseEntity<Void> create(@ApiParam(name = "Rental", value = "Representation of a new Rental", required = true) @Valid @RequestBody RentalInputCreate rent){
        rentalCommandService.create(rent);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
