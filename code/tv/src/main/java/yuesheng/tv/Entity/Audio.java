package yuesheng.tv.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="Audio")
public class Audio {
    @Id
    private int bookId;
    private byte[] text;
    private byte[] audio;
    private byte[] baseAudio;
}
