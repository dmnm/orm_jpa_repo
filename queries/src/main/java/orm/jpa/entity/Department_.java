package orm.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-22T11:19:43.021+0400")
@StaticMetamodel(Department.class)
public class Department_ {
	public static volatile SingularAttribute<Department, Long> id;
	public static volatile SingularAttribute<Department, String> name;
	public static volatile ListAttribute<Department, Employee> employees;
}
