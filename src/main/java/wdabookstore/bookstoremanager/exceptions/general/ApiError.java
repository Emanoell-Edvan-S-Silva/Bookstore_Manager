package wdabookstore.bookstoremanager.exceptions.general;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private String message;

    private List<String> errors;

}
