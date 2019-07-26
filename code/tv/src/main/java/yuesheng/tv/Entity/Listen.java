package yuesheng.tv.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Embeddable
@Table(name="listen", schema="yuesheng", catalog="")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "listenId"
)
public class Listen {
    private int listenId;
    private String time;
    private User listener;
    private Soundbook soundbook;

    @Id
    @Column(name="listenid")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getListenId() {
        return listenId;
    }

    public void setListenId(int listenId) {
        this.listenId = listenId;
    }

    @Basic
    @Column(name="time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "username", unique = true)
    @JsonIgnoreProperties(value={"password", "name", "selfBooks",
            "listenRecord", "favorite", "commentRecord"})
    public User getListener() {
        return listener;
    }

    public void setListener(User listener) {
        this.listener = listener;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "bookid", unique = true)
    @JsonIgnoreProperties(value={"createTime", "commentList"})
    public Soundbook getSoundbook() {
        return soundbook;
    }

    public void setSoundbook(Soundbook soundbook) {
        this.soundbook = soundbook;
    }
}
