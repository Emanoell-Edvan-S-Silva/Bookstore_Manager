package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.controllers.interfaces.PublisherControllerDocs;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.mappers.PublisherMapper;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherResponse;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherCommandService;
import wdabookstore.bookstoremanager.services.interfaces.publisher.PublisherQueryService;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/Publishers")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class PublisherController implements PublisherControllerDocs {
    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private PublisherQueryService publisherQueryServices;

    @Autowired
    private PublisherCommandService publisherCommandService;

    @Override
    public ResponseEntity<List<PublisherResponse>> findAll(){
        List<PublisherEntity> publishersEntities = publisherQueryServices.findAllNotDeleted();
        List<PublisherResponse> publishers = publishersEntities.stream()
                .map(publisherMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(publishers);
    }

    @Override
    public ResponseEntity<PublisherResponse> findById(@PathVariable Long id){
        PublisherResponse publisher = publisherMapper
                .mapperEntityToOutput(publisherQueryServices.findById(id));
        return ResponseEntity.ok().body(publisher);
    }

    @Override
    public ResponseEntity<Void> create(@Valid @RequestBody PublisherInputCreate publisher){
        publisherCommandService.create(publisher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@Valid @RequestBody PublisherInputUpdate publisher){
        publisherCommandService.update(publisher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        publisherCommandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}