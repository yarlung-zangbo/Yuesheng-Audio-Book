package yuesheng.tv.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="soundbook", schema="yuesheng", catalog="")
@JsonIgnoreProperties(value={"handler", "hibernateLazyInitializer", "fieldHandler"
    /*"commentList"*/})
/*
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId"
)
*/
public class Soundbook {
    private int bookId;
    private String name;
    private User creater;
    private int mark;
    private String realeasetime;
    private String disabled;
    private String createTime;
    private List<Comment> commentList;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="bookid")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username")
    @JsonIgnoreProperties(value={"password", "name", "selfBooks",
    "listenRecord", "favorite", "commentRecord"})
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    @Basic
    @Column(name="mark")
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name="releasetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getRealeasetime() {
        return realeasetime;
    }

    public void setRealeasetime(String realease) {
        this.realeasetime = realease;
    }

    @Basic
    @Column(name="disabled")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @Column(name="createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

   /*
    private List<Listen> listenRecord;

    @OneToMany(mappedBy="bookId",cascade=CascadeType.ALL)
    public List<Listen> getListenRecord() {
        return listenRecord;
    }

    public void setListenRecord(List<Listen> listenRecord) {
        this.listenRecord = listenRecord;
    }
    */

   @OneToMany(mappedBy="soundbook", cascade = CascadeType.ALL)
   @JsonIgnoreProperties(value={"soundbook"})
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
