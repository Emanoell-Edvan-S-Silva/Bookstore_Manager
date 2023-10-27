package wdabookstore.bookstoremanager.validations.interfaces;

import wdabookstore.bookstoremanager.dto.book.BookInputCreate;
import wdabookstore.bookstoremanager.dto.book.BookInputUpdate;

public interface BookValidations {
    void validateCreate(BookInputCreate bookInputCreate);

    void validateUpdate(BookInputUpdate bookInputUpdate);

    void validateDelete(Long bookId);

}
