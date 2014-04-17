package orm.jpa.entity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @Basic(optional = true)
    String name;

    @OneToMany(mappedBy = "department", cascade = { PERSIST, MERGE, REMOVE, REFRESH }, orphanRemoval = false)
    List<Employee> employees;
}
