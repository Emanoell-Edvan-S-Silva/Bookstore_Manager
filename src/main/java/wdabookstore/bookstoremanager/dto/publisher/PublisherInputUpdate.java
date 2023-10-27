package wdabookstore.bookstoremanager.dto.publisher;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherInputUpdate {

    @NotNull(message = "Id da editora não foi Informado")
    private Long id;

    @NotBlank(message = "Nome da editora não foi informado!")
    @Size(min = 4,max = 100, message = "O Nome da editora deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String name;

    @NotBlank(message = "Cidade não informada!")
    @Size(min = 4,max = 100, message = "A Cidade deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String city;

}
