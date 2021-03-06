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
import javax.persistence.SqlResultSetMapping;

@Entity
@NamedQueries({ 
    @NamedQuery(name = "Departments.all", query = "select d from Department d"),
    @NamedQuery(name = "Departments.count", query = "select count(d) from Department d"),
    @NamedQuery(name = "Departments.byName", query = "select d from Department d where d.name = :name") })
@SqlResultSetMapping(
    name = "departmentsWithCountOfEmployees", 
    entities = {@EntityResult(entityClass = Department.class)},
    columns = {@ColumnResult(name = "count_of_employees")})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @Basic(optional = true)
    public String name;

    @OneToMany(mappedBy = "department", orphanRemoval = false, cascade = CascadeType.ALL)
    public List<Employee> employees;
}
