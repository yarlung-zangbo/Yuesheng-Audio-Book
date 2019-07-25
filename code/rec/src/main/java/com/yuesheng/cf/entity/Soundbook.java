package com.yuesheng.cf.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yuesheng.cf.tool.FloatFormat;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "soundbook", schema = "yuesheng", catalog = "")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler",
        "commentList", "markList", "disabled"})
/*
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId"
)
*/
public class Soundbook {
    private int bookid;
    private String name;
    private User creater;
    private float mark;
    private String disabled;
    private String createTime;
    private String releasetime;
    private List<Comment> commentList;
    private List<Mark> markList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookid")
    public int getbookid() {
        return bookid;
    }

    public void setbookid(int bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    @Basic
    @Column(name = "mark")
    @JsonSerialize(using = FloatFormat.class)
    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }


    @Basic
    @Column(name = "releasetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    @Basic
    @Column(name = "disabled")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @Column(name = "createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @OneToMany(mappedBy = "soundbook", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"soundbook"})
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @OneToMany(mappedBy = "soundbook", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"soundbook"})
    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }
}
