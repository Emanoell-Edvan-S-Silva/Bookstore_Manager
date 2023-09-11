package wdabookstore.bookstoremanager.entities;

import lombok.Getter;
import lombok.Setter;
import wdabookstore.bookstoremanager.DataBase.Publishers.PublishersEntity;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublishersEntity publisher;


}
