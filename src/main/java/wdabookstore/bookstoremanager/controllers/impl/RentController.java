package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdabookstore.bookstoremanager.controllers.interfaces.RentControllerDocs;
import wdabookstore.bookstoremanager.dto.rent.RentFinalizeRent;
import wdabookstore.bookstoremanager.dto.rent.RentInputCreate;
import wdabookstore.bookstoremanager.dto.rent.RentExtendRent;
import wdabookstore.bookstoremanager.dto.rent.RentResponse;
import wdabookstore.bookstoremanager.entities.RentEntity;
import wdabookstore.bookstoremanager.mappers.RentMapper;
import wdabookstore.bookstoremanager.services.rent.RentCommandService;
import wdabookstore.bookstoremanager.services.rent.RentQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/rents")
public class RentController implements RentControllerDocs {
    @Autowired
    RentQueryService rentQueryService;

    @Autowired
    RentCommandService rentCommandService;

    @Autowired
    RentMapper rentMapper;

    @Override
    public ResponseEntity<List<RentResponse>> findLateAndOnTimeRents(){
        rentCommandService.updateRentStatus();
        List<RentEntity> rentEntities = rentQueryService.getLateAndOnTimeRents();
        List<RentResponse> rent = rentEntities.stream()
                .map(rentMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rent);
    }

    @Override
    public ResponseEntity<List<RentResponse>> findOutstandingRents(){
        rentCommandService.updateRentStatus();
        List<RentEntity> rentEntities = rentQueryService.getOutstandingRents();
        List<RentResponse> rent = rentEntities.stream()
                .map(rentMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(rent);
    }

    @Override
    public ResponseEntity<RentResponse> findById(@PathVariable Long id){
        RentResponse rent = rentMapper
                .mapperEntityToOutput(rentQueryService.findById(id));
        return ResponseEntity.ok().body(rent);
    }

    @Override
    public ResponseEntity<Void> create(@Valid @RequestBody RentInputCreate rent){
        rentCommandService.create(rent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> extendRent(@Valid @RequestBody RentExtendRent rent){
        rentCommandService.extendRent(rent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> finalizeRent(@Valid @RequestBody RentFinalizeRent rent){
        rentCommandService.finalizeRent(rent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        rentCommandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
