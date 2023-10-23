package wdabookstore.bookstoremanager.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdabookstore.bookstoremanager.controllers.interfaces.BookControllerDocs;
import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.book.BookResponse;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.mappers.BookMapper;
import wdabookstore.bookstoremanager.services.interfaces.book.BookCommandService;
import wdabookstore.bookstoremanager.services.interfaces.book.BookQueryService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/Books")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class BookController implements BookControllerDocs {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCommandService bookCommandService;

    @Autowired
    private BookQueryService bookQueryService;

    @Override
    public ResponseEntity<List<BookResponse>> findAll(){
        List<BookEntity> bookOutputDTOS = bookQueryService.findAllNotDeleted();
        List<BookResponse> book = bookOutputDTOS.stream()
                .map(bookMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(book);
    }

    @Override
    public ResponseEntity<BookResponse> findById(@PathVariable Long id){
        BookResponse book = bookMapper
                .mapperEntityToOutput(bookQueryService.findById(id));
        return ResponseEntity.ok().body(book);
    }

    @Override
    public ResponseEntity<List<BookResponse>> findAllAvailableBooks(){
        List<BookEntity> bookOutputDTOS = bookQueryService.findAllAvailableBooks();
        List<BookResponse> book = bookOutputDTOS.stream()
                .map(bookMapper::mapperEntityToOutput)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(book);
    }

    @Override
    public ResponseEntity<Void> create(@Valid @RequestBody BookInputCreate book){
        bookCommandService.create(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@Valid @RequestBody BookInputUpdate book){
        bookCommandService.update(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookCommandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
