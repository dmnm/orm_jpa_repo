package orm.jpa.first.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-02T16:38:21.132+0400")
@StaticMetamodel(FirstEntity.class)
public class FirstEntity_ {
	public static volatile SingularAttribute<FirstEntity, Long> id;
	public static volatile SingularAttribute<FirstEntity, String> name;
	public static volatile SingularAttribute<FirstEntity, Long> amount;
}
