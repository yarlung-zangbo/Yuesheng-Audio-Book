package vt.vtservice.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "bgms")
public class BGMContent {
    @Id
    private String name;
    private byte[] content;
}
