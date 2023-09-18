package wdabookstore.bookstoremanager.controllers.impl;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.controllers.interfaces.BookControllerDocs;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.output.BookOutputDTO;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.mappers.BookMapper;
import wdabookstore.bookstoremanager.services.books.BookCommandService;
import wdabookstore.bookstoremanager.services.books.BookQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController implements BookControllerDocs {

    @Autowired
    private BookCommandService bookCommandService;

    @Autowired
    private BookQueryService bookQueryService;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseEntity<List<BookOutputDTO>> findAll(){
        List<BookEntity> bookOutputDTOS = bookQueryService.findAll();
        List<BookOutputDTO> user = bookOutputDTOS.stream()
                .map(bookMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<BookOutputDTO> findById(@PathVariable Long id){
        BookOutputDTO book = bookMapper
                .mapperEntityToOutput(bookQueryService.findById(id));
        return ResponseEntity.ok().body(book);
    }

    @Override
    public ResponseEntity<Void> create(@Valid @RequestBody BookInputCreate Book){
        bookCommandService.create(Book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@Valid @RequestBody BookInputUpdate publisher){
        bookCommandService.update(publisher);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookCommandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
