package orm.jpa.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @Basic(optional = true)
    public String name;

    @OneToMany(mappedBy = "department", orphanRemoval = false, cascade = CascadeType.ALL)
    public List<Employee> employees;
}
