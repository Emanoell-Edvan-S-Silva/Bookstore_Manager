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
public class RentalExtendRent {

    @NotNull(message = "Campo não informado!")
    private Long id;

    @NotNull(message = "Campo não informado!")
    private LocalDate returnprevisiondate;

}
