package wdabookstore.bookstoremanager.dto.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wdabookstore.bookstoremanager.dto.book.BookRelatedAnswer;
import wdabookstore.bookstoremanager.dto.user.UserRelatedAnswer;
import wdabookstore.bookstoremanager.status.RentalStatus;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {

    private Long id;

    private LocalDate rentaldate;

    private LocalDate previsiondate;

    private LocalDate returndate;

    private RentalStatus status;

    private UserRelatedAnswer user;

    private BookRelatedAnswer book;
}
