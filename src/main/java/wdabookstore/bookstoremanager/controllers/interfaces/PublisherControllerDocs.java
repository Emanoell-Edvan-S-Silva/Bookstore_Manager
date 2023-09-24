package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherResponse;

import javax.validation.Valid;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "Publisher-Actions")
public interface PublisherControllerDocs {

    @ApiOperation(value = "Listar Editoras")
    @GetMapping
    ResponseEntity<List<PublisherResponse>> findAll();

    @ApiOperation(value = "Listar Editora(Id)")
    @GetMapping("/{id}")
    ResponseEntity<PublisherResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Editora")
    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody PublisherInputCreate publisher);

    @ApiOperation(value = "Atualizar Editora")
    @PutMapping
    ResponseEntity<Void> update(@Valid @RequestBody PublisherInputUpdate publisher);

    @ApiOperation(value = "Deletar Editora")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}