package orm.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    public Long id;

    public String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_has_employee", joinColumns = { @JoinColumn(name = "project_id",
            referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "employee_id",
            referencedColumnName = "id") })
    public List<Employee> employees;

}
