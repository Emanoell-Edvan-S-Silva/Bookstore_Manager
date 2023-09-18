package wdabookstore.bookstoremanager.services.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputCreate;
import wdabookstore.bookstoremanager.dto.inputs.book_inputs.BookInputUpdate;
import wdabookstore.bookstoremanager.dto.inputs.publisher_inputs.PublisherInputUpdate;
import wdabookstore.bookstoremanager.dto.output.BookOutputDTO;
import wdabookstore.bookstoremanager.dto.output.PublisherOutputDTO;
import wdabookstore.bookstoremanager.entities.BookEntity;
import wdabookstore.bookstoremanager.entities.PublisherEntity;
import wdabookstore.bookstoremanager.mappers.BookMapper;
import wdabookstore.bookstoremanager.repositories.BookRepository;

import javax.validation.Valid;

@Service
public class BookCommandService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookQueryService bookQueryService;

    @Transactional
    public void create(BookInputCreate publisherdata) {
        PublisherEntity publisher = bookQueryService.findPublisher(publisherdata.getPublisherId());
        BookEntity Book = bookMapper.mapperInputToEntityCreate(publisherdata, publisher);
        bookRepository.save(Book);
    }
    @Transactional
    public void update(@Valid BookInputUpdate request) {
        PublisherEntity publisher = bookQueryService.findPublisher(request.getPublisherId());
        BookEntity Book = bookMapper.mapperInputToEntityUpdate(request, publisher);
        bookRepository.save(Book);
    }

    public void delete(Long id) {
        bookQueryService.findById(id);
        try {
            this.bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há Aluguéis relacionados");
        }
    }
}
