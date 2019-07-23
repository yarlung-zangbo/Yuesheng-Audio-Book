package vt.vtservice.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "AduioAudio")
public class AudioAduio {
    @Id
    private int bookId;
    private byte[] audio;
    private byte[] baseAudio;
}
