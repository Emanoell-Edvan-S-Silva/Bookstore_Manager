package wdabookstore.bookstoremanager.dto.output.book_outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wdabookstore.bookstoremanager.dto.output.publisher_outputs.PublisherResponse;

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

    private int total_leased;

    private PublisherResponse publisher;
}
