package wdabookstore.bookstoremanager.exceptions.error_message;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problem")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ErrorMessageModel {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "2020-10-24T15:34:20.6Z", position = 5)
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "https://localhost/invalid-data", position = 10)
    private String type;

    @ApiModelProperty(example = "Invalid data", position = 15)
    private String title;

    @ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", position = 20)
    private String detail;

    @ApiModelProperty(value = "List of objects or fields that generated or error (optional)", position = 30,
            notes = "Test oi")
    private List<String> errors;

}
