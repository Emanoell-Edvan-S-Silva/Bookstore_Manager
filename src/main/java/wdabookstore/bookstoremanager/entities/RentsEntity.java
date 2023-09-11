package wdabookstore.bookstoremanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
