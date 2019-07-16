package yuesheng.tv.Entity;

import lombok.Data;
import yuesheng.tv.Entity.EntityKey.EmotionMapKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "EmotionMap")
@Entity
public class EmotionMap {
    @EmbeddedId
    private EmotionMapKey emotionMapKey;

    @Column(name = "relevancy")
    private int relevancy;
}
