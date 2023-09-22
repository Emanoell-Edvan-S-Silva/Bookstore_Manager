package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.output.book_outputs.BookResponse;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
@Api(tags = "Book-Actions")
public interface BookControllerDocs {

    @ApiOperation(value = "Listar Livros")
    @GetMapping
    ResponseEntity<List<BookResponse>> findAll();

    @ApiOperation(value = "Listar Livro(Id)")
    @GetMapping("/{id}")
    ResponseEntity<BookResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Criar Novo Livro")
    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody BookInputCreate Book);

    @ApiOperation(value = "Atualizar Livro")
    @PutMapping
    ResponseEntity<Void> update(@Valid @RequestBody BookInputUpdate publisher);

    @ApiOperation(value = "Deletar Livro")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
