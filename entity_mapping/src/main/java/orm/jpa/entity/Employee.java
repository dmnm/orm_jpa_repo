package orm.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Employee {

    @Id
    Long id;

    String firstName;

    String secondName;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    Department department;

    @ManyToMany
    @JoinTable(name = "projects_employees")
    List<Project> projects;
}
