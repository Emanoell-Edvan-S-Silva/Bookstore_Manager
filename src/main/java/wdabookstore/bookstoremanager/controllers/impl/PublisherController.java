package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.controllers.interfaces.PublisherControllerDocs;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputCreate;
import wdabookstore.bookstoremanager.mappers.PublisherMapper;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
import wdabookstore.bookstoremanager.services.publisher.PublisherCommandService;
import wdabookstore.bookstoremanager.services.publisher.PublisherQueryService;
import wdabookstore.bookstoremanager.entities.PublisherEntity;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/publisher")
@Validated
public class PublisherController implements PublisherControllerDocs {
    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private PublisherQueryService publisherQueryServices;

    @Autowired
    private PublisherCommandService publisherCommandService;

    @Override
    public ResponseEntity<List<PublisherOutputDTO>> findAll(){
        List<PublisherEntity> publishersEntities = publisherQueryServices.findAll();
        List<PublisherOutputDTO> publishers = publishersEntities.stream()
                .map(publisherMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(publishers);
    }
    @Override
    public ResponseEntity<PublisherOutputDTO> findById(@PathVariable Long id){
        PublisherOutputDTO publisher = publisherMapper
                .mapperEntityToOutput(publisherQueryServices.findById(id));
        return ResponseEntity.ok().body(publisher);
    }

    @Override
    public ResponseEntity<PublisherOutputDTO> create(@Valid @RequestBody PublisherInputCreate publisher){
        PublisherOutputDTO createdPublisher = publisherCommandService.create(publisher);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<PublisherOutputDTO> update(@Valid @RequestBody PublisherInputUpdate publisher){
        PublisherOutputDTO updatedPublisher = publisherCommandService.update(publisher);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        publisherCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}