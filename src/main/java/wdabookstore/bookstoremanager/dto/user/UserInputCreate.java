package wdabookstore.bookstoremanager.dto.user;

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
public class UserInputCreate {

    @NotBlank(message = "Nome do Usuário não foi informado!")
    @Size(min = 4,max = 100, message = "O Nome do Usuário deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private  String name;

    @NotBlank(message = "Cidade não foi informada!")
    @Size(min = 4,max = 100, message = "A cidade deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String city;

    @NotBlank(message = "E-mail não informado!")
    @Size(min = 4,max = 100, message = "O E-mail deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String email;

    @NotBlank(message = "Endereço não foi informado!")
    @Size(min = 4,max = 100, message = "O endereço deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String address;
}
