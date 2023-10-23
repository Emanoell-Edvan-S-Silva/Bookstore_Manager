package wdabookstore.bookstoremanager.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import wdabookstore.bookstoremanager.status.RentalStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_rentals")
public class RentalEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        @ApiModelProperty(example = "XXXX-XX-XX")
        private LocalDate rentaldate;

        @Column(nullable = false)
        @ApiModelProperty(example = "XXXX-XX-XX")
        private LocalDate previsiondate;

        @ApiModelProperty(example = "XXXX-XX-XX")
        private LocalDate returndate;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private RentalStatus status;

        @ManyToOne
        @JoinColumn(name = "user_entity_id")
        private UserEntity userEntity;

        @ManyToOne
        @JoinColumn(name = "book_entity_id")
        private BookEntity bookEntity;

        private boolean deleted;
}
