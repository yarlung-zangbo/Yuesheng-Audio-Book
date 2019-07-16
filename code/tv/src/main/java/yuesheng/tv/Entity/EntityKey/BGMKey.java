package yuesheng.tv.Entity.EntityKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BGMKey implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "mood")
    private String mood;

    @Override
    public String toString(){
        return name+mood;
    }
}
