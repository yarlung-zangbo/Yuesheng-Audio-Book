package vt.vtservice.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "bgmnames")
@Entity
public class BGMName {
    @Id
    private String name;
}
