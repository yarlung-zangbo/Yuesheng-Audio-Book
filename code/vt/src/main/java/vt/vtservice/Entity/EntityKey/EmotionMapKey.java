package vt.vtservice.Entity.EntityKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class EmotionMapKey implements Serializable {
    @Column(name = "mood")
    String mood;
    @Column(name = "sense")
    String sense;
    @Override
    public String toString(){
        return mood+sense;
    }
}
