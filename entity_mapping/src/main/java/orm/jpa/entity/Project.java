package orm.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {

    @Id
    Long id;

    String name;

}
