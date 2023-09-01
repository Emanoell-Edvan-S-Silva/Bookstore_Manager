package wdabookstore.bookstoremanager.entities.Publishers;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import wdabookstore.bookstoremanager.entities.Books.BooksEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class PublishersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private  String name;

    @Column(nullable = false, length = 100)
    private String city;

    @OneToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<BooksEntity> booksEntities;
}
