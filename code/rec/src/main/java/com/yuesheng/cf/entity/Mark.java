package com.yuesheng.cf.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "mark", schema = "yuesheng", catalog = "")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "markid"
)
public class Mark {
    private int markid;
    private int score;
    private User user;
    private Soundbook soundbook;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "markid")
    public int getMarkid() {
        return markid;
    }

    public void setMarkid(int markid) {
        this.markid = markid;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties(value = {"mark", "createTime", "releasetime", "creater"})
    public Soundbook getSoundbook() {
        return soundbook;
    }

    public void setSoundbook(Soundbook soundbook) {
        this.soundbook = soundbook;
    }
}
