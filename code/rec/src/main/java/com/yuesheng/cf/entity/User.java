package com.yuesheng.cf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "yuesheng", catalog = "")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler",
        "markRecord", "commentRecord",})
/*
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username"
)
*/
public class User {

    private String username;
    private String name;
    private List<Mark> markRecord;
    private List<Comment> commentRecord;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username", length = 64)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "name", length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Comment> getCommentRecord() {
        return commentRecord;
    }

    public void setCommentRecord(List<Comment> commentRecord) {
        this.commentRecord = commentRecord;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Mark> getMarkRecord() {
        return markRecord;
    }

    public void setMarkRecord(List<Mark> markRecord) {
        this.markRecord = markRecord;
    }

}
