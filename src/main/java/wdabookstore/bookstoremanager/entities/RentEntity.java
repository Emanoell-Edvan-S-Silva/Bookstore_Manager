package wdabookstore.bookstoremanager.entities;

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
        private Date rentdate;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        private Date forecastdate;

        @Temporal(TemporalType.DATE)
        private Date returndate;

        @Column
        private String status;

        @ManyToOne(cascade = {CascadeType.MERGE})
        private UserEntity userEntity;

        @ManyToOne(cascade = {CascadeType.MERGE})
        private BookEntity bookEntity;

}
