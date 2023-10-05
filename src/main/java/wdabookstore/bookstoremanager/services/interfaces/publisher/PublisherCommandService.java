package wdabookstore.bookstoremanager.services.interfaces.publisher;

import wdabookstore.bookstoremanager.dto.publisher.PublisherInputCreate;
import wdabookstore.bookstoremanager.dto.publisher.PublisherInputUpdate;

import javax.validation.Valid;

public interface PublisherCommandService {
    void create(PublisherInputCreate request);

    void update(@Valid PublisherInputUpdate request);

    void delete(Long id);

}
