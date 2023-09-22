package wdabookstore.bookstoremanager.dto.output.publisher_outputs;

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
