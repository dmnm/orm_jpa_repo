package orm.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-03T20:38:01.709+0400")
@StaticMetamodel(Project.class)
public class Project_ {
	public static volatile SingularAttribute<Project, String> name;
	public static volatile ListAttribute<Project, Employee> employees;
	public static volatile SingularAttribute<Project, Long> id;
}
