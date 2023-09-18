package wdabookstore.bookstoremanager.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {

    private Long id;

    private  String name;

    private String city;

    private String email;

    private String address;

    private int active_rentals;
}
