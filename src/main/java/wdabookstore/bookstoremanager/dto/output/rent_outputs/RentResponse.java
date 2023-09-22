package wdabookstore.bookstoremanager.dto.output.rent_outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wdabookstore.bookstoremanager.dto.output.user_outputs.UserResponse;
import wdabookstore.bookstoremanager.dto.output.book_outputs.BookResponse;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentResponse {

    private Long id;

    private Date rentdate;

    private Date forecastdate;

    private Date returndate;

    private String status;

    private UserResponse user;

    private BookResponse book;
}
