package vt.vtservice.Entity;

import lombok.Data;
import vt.vtservice.Entity.EntityKey.BGMKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "bgm")
@Entity
public class BGM {
    @EmbeddedId
    private BGMKey key;
}
