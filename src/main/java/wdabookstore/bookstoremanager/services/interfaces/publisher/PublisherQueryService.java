package wdabookstore.bookstoremanager.services.interfaces.publisher;

import wdabookstore.bookstoremanager.entities.PublisherEntity;

import java.util.List;

public interface PublisherQueryService {
    List<PublisherEntity> findAllNotDeleted();

    PublisherEntity findById(Long id);

    boolean publisherNameExist(String name);
}
