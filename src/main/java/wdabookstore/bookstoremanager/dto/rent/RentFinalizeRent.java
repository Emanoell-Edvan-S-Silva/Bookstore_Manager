package wdabookstore.bookstoremanager.dto.rent;

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
public class RentFinalizeRent {

    @NotNull(message = "Campo não informado!")
    private Long id;

    @NotNull(message = "Campo não informado!")
    private Date returndate;
}
