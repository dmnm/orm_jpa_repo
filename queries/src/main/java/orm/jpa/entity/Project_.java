package orm.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-22T11:19:43.057+0400")
@StaticMetamodel(Project.class)
public class Project_ {
	public static volatile SingularAttribute<Project, Long> id;
	public static volatile SingularAttribute<Project, String> name;
	public static volatile ListAttribute<Project, Employee> employees;
}
