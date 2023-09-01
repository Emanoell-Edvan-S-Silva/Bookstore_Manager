package wdabookstore.bookstoremanager.entities.Users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import wdabookstore.bookstoremanager.entities.Rents.RentsEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private  String name;

    @Column(nullable = false, unique = true, length = 100)
    private  String email;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int activerentals;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int totalrents;

    @OneToMany(mappedBy = "rents", fetch = FetchType.LAZY)
    private List<RentsEntity> RentsEntities;
}
