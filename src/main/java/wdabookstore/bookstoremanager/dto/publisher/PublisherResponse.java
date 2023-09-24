package wdabookstore.bookstoremanager.dto.publisher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {

    private Long id;

    private  String name;

    private String city;
}
