package wdabookstore.bookstoremanager.controllers.impl;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wdabookstore.bookstoremanager.Mappers.PublisherMapper;
import wdabookstore.bookstoremanager.dto.inputs.PublisherInputDTO;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
import wdabookstore.bookstoremanager.services.publisher.PublisherCommandService;
import wdabookstore.bookstoremanager.services.publisher.PublisherQueryService;
import wdabookstore.bookstoremanager.entities.PublisherEntity;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/publisher")
@Validated
public class PublisherController {
    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private PublisherQueryService publisherQueryServices;

    @Autowired
    private PublisherCommandService publisherCommandService;

    @ApiOperation(value = "Listar Editoras")
    @GetMapping
    public ResponseEntity<List<PublisherOutputDTO>> findAll(){
        List<PublisherEntity> publishersEntities = publisherQueryServices.findAll();
        List<PublisherOutputDTO> publishers = publishersEntities.stream()
                .map(publisherMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(publishers);
    }

    @ApiOperation(value = "Listar Editora(Id)")
    @GetMapping("/{id}")
    public ResponseEntity<PublisherOutputDTO> findById(@PathVariable Long id){
        PublisherOutputDTO publisher = publisherMapper
                .mapperEntityToOutput(publisherQueryServices.findById(id));
        return ResponseEntity.ok().body(publisher);
    }

    @ApiOperation(value = "Criar Editora")
    @PostMapping
    public ResponseEntity<PublisherOutputDTO> create(@Valid @RequestBody PublisherInputDTO publisher){
        PublisherOutputDTO createdPublisher = publisherCommandService.create(publisher);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Atualizar Editora")
    @PutMapping
    public ResponseEntity<PublisherOutputDTO> update(@Valid @RequestBody PublisherInputDTO publisher){
        PublisherOutputDTO updatedPublisher = publisherCommandService.update(publisher);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }
    @ApiOperation(value = "Deletar Editora")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        publisherCommandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
