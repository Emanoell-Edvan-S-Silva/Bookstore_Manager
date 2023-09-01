package wdabookstore.bookstoremanager.entities.Books;

import lombok.Getter;
import lombok.Setter;
import wdabookstore.bookstoremanager.entities.Publishers.PublishersEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class BooksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 4)
    private  String launch;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int amount;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int totalrent;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<PublishersEntity> PublisherEntities;

}
