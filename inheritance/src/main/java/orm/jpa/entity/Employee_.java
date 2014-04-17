package orm.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-17T12:42:59.185+0400")
@StaticMetamodel(Employee.class)
public class Employee_ {
	public static volatile SingularAttribute<Employee, String> firstName;
	public static volatile SingularAttribute<Employee, String> secondName;
	public static volatile SingularAttribute<Employee, Department> department;
	public static volatile ListAttribute<Employee, Project> projects;
	public static volatile SingularAttribute<Employee, Long> id;
}
