package yuesheng.tv.Entity;

import lombok.Data;
import yuesheng.tv.Entity.EntityKey.BGMKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "BGM")
@Entity
public class BGM {
    @EmbeddedId
    private BGMKey key;
}
