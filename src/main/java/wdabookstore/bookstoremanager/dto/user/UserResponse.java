package wdabookstore.bookstoremanager.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private  String name;

    private String city;

    private String email;

    private String address;

}
