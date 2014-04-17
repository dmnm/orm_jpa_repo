package orm.jpa.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PJ")
public class Programmer extends Employee {

    public String primaryLanguage;
    public String secondaryLanguage;
}
