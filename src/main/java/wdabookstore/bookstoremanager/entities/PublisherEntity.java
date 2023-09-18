package wdabookstore.bookstoremanager.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_publishers")
public class PublisherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private  String name;

    @Column(nullable = false, length = 100)
    private String city;

    @ApiModelProperty(hidden = true)
    @OneToMany(mappedBy = "publisherEntity")
    private List<BookEntity> books;

}
