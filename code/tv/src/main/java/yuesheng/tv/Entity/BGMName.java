package yuesheng.tv.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "BGMNames")
@Entity
public class BGMName {
    @Id
    private String name;
}
