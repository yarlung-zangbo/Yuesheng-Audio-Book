package yuesheng.tv.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TextAudio")
public class TextAudio {
    private String id;
    private int bookId;
    private byte[] text;
    private byte[] audio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }
}
