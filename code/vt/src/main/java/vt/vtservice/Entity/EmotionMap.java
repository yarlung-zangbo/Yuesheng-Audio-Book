package vt.vtservice.Entity;

import lombok.Data;
import vt.vtservice.Entity.EntityKey.EmotionMapKey;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "emotionmap")
@Entity
public class EmotionMap {
    @EmbeddedId
    private EmotionMapKey emotionMapKey;

    @Column(name = "relevancy")
    private int relevancy;
}
