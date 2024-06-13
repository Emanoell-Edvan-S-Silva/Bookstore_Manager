package wdabookstore.bookstoremanager.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wdabookstore.bookstoremanager.dto.publisher.PublisherRelatedAnswer;
import wdabookstore.bookstoremanager.dto.publisher.PublisherResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;

    private String name;

    private String author;

    private Integer launch;

    private int amount;

    private int active_rentals;

    private PublisherRelatedAnswer publisher;
}
