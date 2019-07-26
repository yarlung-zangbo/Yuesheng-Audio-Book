package vt.vtservice.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user", schema="yuesheng", catalog="")
@JsonIgnoreProperties(value={"handler", "hibernateLazyInitializer", "fieldHandler",
       /* "password", "selfBooks", "listenRecord", "favorite", "commentRecord" */})
/*
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username"
)
*/
public class User {

    private String userName;
    private String email;
    private String password;
    private int gender;
    private String name;
    private String disabled;
    private List<Soundbook> selfBooks;
    private List<Listen> listenRecord;
    private List<Soundbook> favorite;
    private List<Comment> commentRecord;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="username", length=64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name="email", length=64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name="password", length=64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name="gender")
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name="name", length=64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @OneToMany(cascade={CascadeType.REMOVE},fetch=FetchType.LAZY,
            targetEntity = Soundbook.class,mappedBy = "creater")
    @JsonIgnoreProperties(value={"creater", "commentList"})
    @OrderBy("createTime DESC ")
    public List<Soundbook> getSelfBooks() {
        return selfBooks;
    }

    public void setSelfBooks(List<Soundbook> selfBooks) {
        this.selfBooks = selfBooks;
    }

    @OneToMany(mappedBy="listener",cascade=CascadeType.ALL)
    @JsonIgnoreProperties(value={"listener"})
    @OrderBy(value="time Desc")
    public List<Listen> getListenRecord() {
        return listenRecord;
    }

    public void setListenRecord(List<Listen> listenRecord) {
        this.listenRecord = listenRecord;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="favorite", joinColumns=@JoinColumn(name="username"),
            inverseJoinColumns = @JoinColumn(name="bookid"))
    @JsonIgnoreProperties(value={"disabled", "createTime", "commentList"})
    public List<Soundbook> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Soundbook> favorite) {
        this.favorite = favorite;
    }

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value={"user"})
    public List<Comment> getCommentRecord() {
        return commentRecord;
    }

    public void setCommentRecord(List<Comment> commentRecord) {
        this.commentRecord = commentRecord;
    }
}
