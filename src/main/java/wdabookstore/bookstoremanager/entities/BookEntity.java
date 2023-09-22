package wdabookstore.bookstoremanager.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer launch;

    @Column(nullable = false)
    private int amount;

    @Column
    private int total_leased;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private PublisherEntity publisherEntity;

    @ApiModelProperty(hidden = true)
    @OneToMany(mappedBy = "bookEntity")
    private List<RentEntity> rents;

}