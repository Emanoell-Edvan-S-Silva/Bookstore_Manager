package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wdabookstore.bookstoremanager.controllers.interfaces.RentControllerDocs;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.rent_inputs.RentInputUpdate;
import wdabookstore.bookstoremanager.dto.output.rent_outputs.RentResponse;
import wdabookstore.bookstoremanager.entities.RentEntity;
import wdabookstore.bookstoremanager.mappers.RentMapper;
import wdabookstore.bookstoremanager.services.rent.RentCommandService;
import wdabookstore.bookstoremanager.services.rent.RentQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<RentResponse>> findAll(){
        List<RentEntity> rentEntities = rentQueryService.findAll();
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
    public ResponseEntity<Void> create(@Valid @RequestBody RentInputCreate rentInputCreate){
        rentCommandService.create(rentInputCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@Valid @RequestBody RentInputUpdate rentInputUpdate){
        rentCommandService.update(rentInputUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        rentCommandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
