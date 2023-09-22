package wdabookstore.bookstoremanager.dto.inputs.book_inputs;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wdabookstore.bookstoremanager.notes.YearConstraint;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookInputUpdate {
    @NotNull(message = "Campo não Informado")
    private Long id;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo nome deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String name;

    @NotBlank(message = "Campo não informado!")
    @Size(min = 4,max = 100, message = "O campo nome deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String author;

    @NotNull
    @YearConstraint
    @ApiModelProperty(required = true)
    private Integer launch;

    @NotNull(message = "Campo não informado!")
    @Min(value = 1, message = "O valor deve ser maior que 0")
    private int amount;

    @NotNull(message = "Campo não informado!")
    private Long publisherId;

}
