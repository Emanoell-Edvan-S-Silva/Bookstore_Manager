package wdabookstore.bookstoremanager.entities.Rents;

import lombok.Getter;
import lombok.Setter;
import wdabookstore.bookstoremanager.entities.Books.BooksEntity;
import wdabookstore.bookstoremanager.entities.Users.UsersEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class RentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rentdate;

    @Column(nullable = false)
    private String forecastdate;

    @Column(nullable = false)
    private String returndate;

    @Column
    private String status;

    @OneToOne(mappedBy = "books", fetch = FetchType.LAZY)
    private List<BooksEntity> BooksEntities;

    @OneToOne(mappedBy = "users", fetch = FetchType.LAZY)
    private List<UsersEntity> UserEntities;

}
