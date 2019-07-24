package vt.vtservice.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "Audio")
public class Aduio {
    @Id
    private int bookId;
    private byte[] audio;
    private byte[] text;
    private byte[] baseAudio;
}
