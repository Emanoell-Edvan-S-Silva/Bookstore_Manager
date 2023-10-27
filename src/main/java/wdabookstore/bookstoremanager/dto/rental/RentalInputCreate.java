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
    @NotNull(message = "Data de Previsão não foi informadoa")
    private LocalDate previsiondate;

    @NotNull(message = "Id do Usuario não foi informado!")
    private Long userId;

    @NotNull(message = "Id do Livro não foi informado!")
    private Long bookId;
}
