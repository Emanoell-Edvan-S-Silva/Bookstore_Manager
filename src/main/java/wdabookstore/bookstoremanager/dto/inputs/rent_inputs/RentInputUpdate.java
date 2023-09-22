package wdabookstore.bookstoremanager.dto.inputs.rent_inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentInputUpdate {

    @NotNull(message = "Campo não Informado")
    private Long id;

    @NotNull(message = "Campo não informado!")
    private Date forecastdate;

    @NotNull(message = "Campo não informado!")
    private Date returndate;

    @NotNull(message = "Campo não informado!")
    private Long userId;

    @NotNull(message = "Campo não informado!")
    private Long bookId;
}
