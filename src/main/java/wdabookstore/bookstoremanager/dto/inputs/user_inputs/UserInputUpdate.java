package wdabookstore.bookstoremanager.dto.inputs.user_inputs;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInputUpdate {

    private Long id;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo nome deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private  String name;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo de deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String city;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo de deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String email;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo de deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String address;
}
