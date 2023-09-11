package wdabookstore.bookstoremanager.controllers.impl;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wdabookstore.bookstoremanager.dto.inputs.PublisherInput;
import wdabookstore.bookstoremanager.services.publisher.PublisherCommandService;
import wdabookstore.bookstoremanager.services.publisher.PublisherQueryService;
import wdabookstore.bookstoremanager.entities.PublisherEntity;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/publisher")
@Validated
public class PublisherController {

    @Autowired
    private PublisherQueryService publisherQueryServices;

    @Autowired
    private PublisherCommandService publisherCUDService;

    @ApiOperation(value = "Listar Editoras")
    @GetMapping
    public ResponseEntity<List<PublisherEntity>> findAll(){
        List<PublisherEntity> publishers = publisherQueryServices.findAllPublishers();
        return ResponseEntity.ok().body(publishers);
    }
    @ApiOperation(value = "Listar Editora(Id)")
    @GetMapping("/{id}")
    public ResponseEntity<PublisherEntity> findById(@PathVariable Long id){
        PublisherEntity publisher = publisherQueryServices.findByIdPublisher(id);
        return ResponseEntity.ok().body(publisher);
    }
    @ApiOperation(value = "Criar Editora")
    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody PublisherInput publisher){
        publisherCUDService.createPublisher(publisher);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(publisher.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @ApiOperation(value = "Atualizar Editora")
    @PutMapping
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody PublisherInput publisher){
        publisherCUDService.updatePublisher(publisher);
        return ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Deletar Editora")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        publisherCUDService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }


}
