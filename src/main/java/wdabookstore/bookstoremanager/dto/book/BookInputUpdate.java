package wdabookstore.bookstoremanager.dto.book;

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
    @NotNull(message = "ID do livro não foi informado!")
    private Long id;

    @NotBlank(message = "Nome do Livro não foi informado!")
    @Size(min = 4,max = 100, message = "O campo nome deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String name;

    @NotBlank(message = "Autor não foi informado!")
    @Size(min = 4,max = 100, message = "O campo Autor deve ter entre 4 e 100 caracteres")
    @ApiModelProperty(required = true)
    private String author;

    @NotNull
    @YearConstraint
    @ApiModelProperty(required = true)
    private Integer launch;

    @NotNull(message = "Quantidade em estoque não foi informado!")
    @Min(value = 1, message = "A quantidade em estoque deve ser maior que 0")
    private int amount;

    @NotNull(message = "ID da editora não foi informado!")
    private Long publisherId;

}
