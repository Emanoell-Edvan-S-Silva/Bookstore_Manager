package wdabookstore.bookstoremanager.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wdabookstore.bookstoremanager.entities.PublisherEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookOutputDTO {

    private Long id;

    private String name;

    private String author;

    private Integer launch;

    private int amount;

    private int total_leased;

    private PublisherOutputDTO publisher;
}
