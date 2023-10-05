package wdabookstore.bookstoremanager.services.interfaces.book;

import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;

import javax.validation.Valid;

public interface BookCommandService {
    void create(BookInputCreate bookInputCreate);

    void update(@Valid BookInputUpdate bookInputUpdate);

    void delete(Long id);
}
