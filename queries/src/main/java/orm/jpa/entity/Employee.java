package orm.jpa.entity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.REMOVE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PROF")
@DiscriminatorValue("EMP")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    Long id;

    public String firstName;

    public String secondName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = { PERSIST, MERGE, REMOVE, REFRESH })
    @JoinColumn(name = "department_id")
    public Department department;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.ALL)
    public List<Project> projects;
}
