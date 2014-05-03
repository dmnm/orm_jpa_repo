package orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.jpa.entity.Company;
import orm.jpa.entity.User;

public class Main {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("default");
    private static final EntityManager EM = EMF.createEntityManager();

    public static void main(final String[] args) {
        try {
            first();
            // second();
        } catch (final Exception e) {
            System.out.println(e);
        } finally {
            EM.close();
            EMF.close();
        }
    }

    private static void first() {
        EM.getTransaction().begin();

        final User a = new User();
        a.setFirstName("firstName");
        a.setSecondName("secondName");

        EM.persist(a);

        final Company company = new Company();
        company.name = "Exigen";
        company.home = "US";

        EM.persist(company);

        // yet another entity?
        company.name = "ROI";
        EM.persist(company);

        EM.getTransaction().commit();

        EM.close();
    }

}
