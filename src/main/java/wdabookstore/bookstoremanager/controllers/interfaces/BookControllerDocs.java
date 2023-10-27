package wdabookstore.bookstoremanager.controllers.interfaces;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.book.BookResponse;

import javax.validation.Valid;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Houve um erro interno")
})
@SuppressWarnings("unused")
@Api(tags = "Book-Actions")
public interface BookControllerDocs {

    @ApiOperation(value = "Listar Todos os Livros")
    @GetMapping
    ResponseEntity<List<BookResponse>> findAll();

    @ApiOperation(value = "Listar Livro(Id)")
    @GetMapping("/{id}")
    ResponseEntity<BookResponse> findById(@PathVariable Long id);

    @ApiOperation(value = "Listar Livros Disponiveis")
    @GetMapping("/AvailableBooks")
    ResponseEntity<List<BookResponse>> findAllAvailableBooks();

    @ApiOperation(value = "Criar Novo Livro")
    @PostMapping
    ResponseEntity<Void> create(@ApiParam(name = "Book", value = "Representation of a new book", required = true) @Valid @RequestBody BookInputCreate Book);

    @ApiOperation(value = "Atualizar Livro")
    @PutMapping
    ResponseEntity<Void> update(@ApiParam(name = "Book", value = "Representation of book modification", required = true) @Valid @RequestBody BookInputUpdate publisher);

    @ApiOperation(value = "Deletar Livro")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
