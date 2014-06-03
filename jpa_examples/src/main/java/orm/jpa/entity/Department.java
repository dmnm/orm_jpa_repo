package orm.jpa.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ 
    @NamedQuery(name = "Departments.all", query = "select d from Department d"),
    @NamedQuery(name = "Departments.count", query = "select count(d) from Department d"),
    @NamedQuery(name = "Departments.byName", query = "select d from Department d where d.name = :name") })
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @Basic(optional = true)
    public String name;

    @OneToMany(mappedBy = "department", orphanRemoval = false, cascade = CascadeType.ALL)
    public List<Employee> employees;

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + "]";
    }
}
