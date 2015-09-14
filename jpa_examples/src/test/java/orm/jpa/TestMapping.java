package orm.jpa;

import org.junit.Test;

import orm.jpa.entity.Company;
import orm.jpa.entity.User;

public class TestMapping extends TestCasesJpa {

    @Test
    public void test_mapping() {

        final User a = new User();
        a.setFirstName("firstName");
        a.setSecondName("secondName");

        em.persist(a);

        final Company company = new Company();
        company.setName("Exigen");
        company.setHome("US");

        em.persist(company);

        // yet another entity?
        company.setName("ROI");

        em.persist(company);
    }
}
