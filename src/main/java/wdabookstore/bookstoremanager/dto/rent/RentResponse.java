package wdabookstore.bookstoremanager.dto.rent;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wdabookstore.bookstoremanager.dto.book.BookRelatedAnswer;
import wdabookstore.bookstoremanager.dto.user.UserRelatedAnswer;
import wdabookstore.bookstoremanager.dto.user.UserResponse;
import wdabookstore.bookstoremanager.dto.book.BookResponse;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentResponse {

    private Long id;

    @ApiModelProperty(example = "2023-09-22")
    private Date rentdate;

    @ApiModelProperty(example = "2023-09-22")
    private Date forecastdate;

    @ApiModelProperty(example = "2023-09-22")
    private Date returndate;

    private String status;

    private UserRelatedAnswer user;

    private BookRelatedAnswer book;
}
