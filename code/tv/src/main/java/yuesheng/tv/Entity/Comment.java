package yuesheng.tv.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name="comment", schema="yuesheng", catalog="")
@JsonIgnoreProperties(value={"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "commentId"
)
public class Comment {
    private int commentId;
    private User user;
    private Soundbook soundbook;
    private String time;
    private String content;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="commentid")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username")
    @JsonIgnoreProperties(value={"password", "name", "selfBooks",
            "listenRecord", "favorite", "commentRecord"})
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bookid")
    @JsonIgnoreProperties(value={"createTime", "commentList"})
    public Soundbook getSoundbook() {
        return soundbook;
    }

    public void setSoundbook(Soundbook soundbook) {
        this.soundbook = soundbook;
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

    @Basic
    @Column(name="content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
