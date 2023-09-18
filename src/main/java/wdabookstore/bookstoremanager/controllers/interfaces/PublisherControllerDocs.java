package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
@Api(tags = "Publisher-Actions")
public interface PublisherControllerDocs {

    @ApiOperation(value = "Listar Editoras")
    @GetMapping
    ResponseEntity<List<PublisherOutputDTO>> findAll();

    @ApiOperation(value = "Listar Editora(Id)")
    @GetMapping("/{id}")
    ResponseEntity<PublisherOutputDTO> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Editora")
    @PostMapping
    ResponseEntity<PublisherOutputDTO> create(@Valid @RequestBody PublisherInputCreate publisher);

    @ApiOperation(value = "Atualizar Editora")
    @PutMapping
    ResponseEntity<PublisherOutputDTO> update(@Valid @RequestBody PublisherInputUpdate publisher);

    @ApiOperation(value = "Deletar Editora")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}