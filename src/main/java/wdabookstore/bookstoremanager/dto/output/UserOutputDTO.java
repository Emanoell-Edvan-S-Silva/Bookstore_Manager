package wdabookstore.bookstoremanager.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {

    @NotBlank
    private Long id;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo nome deve ter entre 4 e 100 caracteres")
    private  String name;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo de deve ter entre 4 e 100 caracteres")
    private String city;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo de deve ter entre 4 e 100 caracteres")
    private String email;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo de deve ter entre 4 e 100 caracteres")
    private String address;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo de deve ter entre 4 e 100 caracteres")
    private int active_rentals;
}
