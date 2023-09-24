package wdabookstore.bookstoremanager.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_rents")
public class RentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        @ApiModelProperty(example = "2023-09-22")
        private Date rentdate;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        @ApiModelProperty(example = "2023-09-22")
        private Date forecastdate;

        @Temporal(TemporalType.DATE)
        @ApiModelProperty(example = "2023-09-22")
        private Date returndate;

        @Column
        private String status;

        @ManyToOne(cascade = {CascadeType.MERGE})
        private UserEntity userEntity;

        @ManyToOne(cascade = {CascadeType.MERGE})
        private BookEntity bookEntity;

}
