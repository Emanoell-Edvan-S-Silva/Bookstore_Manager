package wdabookstore.bookstoremanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_users")
public class UserEntity {
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
    private int active_rentals;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int total_rents;
}
