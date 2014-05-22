package orm.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-22T11:19:43.041+0400")
@StaticMetamodel(Programmer.class)
public class Programmer_ extends Employee_ {
	public static volatile SingularAttribute<Programmer, String> primaryLanguage;
	public static volatile SingularAttribute<Programmer, String> secondaryLanguage;
}
