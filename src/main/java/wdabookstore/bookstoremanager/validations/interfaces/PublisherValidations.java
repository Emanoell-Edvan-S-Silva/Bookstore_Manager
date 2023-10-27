package wdabookstore.bookstoremanager.validations.interfaces;

import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;

public interface PublisherValidations {
    void validateCreate(PublisherInputCreate publisherInputCreate);

    void validateUpdate(PublisherInputUpdate publisherInputUpdate);

    void validateDelete(Long publisherId);
}
