package wdabookstore.bookstoremanager.dto.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalInputCreate {
    @NotNull(message = "Campo não informado!")
    private LocalDate previsiondate;

    @NotNull(message = "Campo não informado!")
    private Long userId;

    @NotNull(message = "Campo não informado!")
    private Long bookId;
}
