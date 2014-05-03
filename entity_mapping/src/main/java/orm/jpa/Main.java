package orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.jpa.entity.Company;
import orm.jpa.entity.User;

public class Main {

    public static void main(final String[] args) {
        try {
            final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            final EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            final User a = new User();
            a.setFirstName("firstName");
            a.setSecondName("secondName");

            em.persist(a);

            final Company company = new Company();
            company.name = "Exigen";
            company.home = "US";

            em.persist(company);

            // yet another entity?
            company.name = "ROI";
            em.persist(company);

            em.getTransaction().commit();

            em.close();
            emf.close();
        } catch (final Exception e) {
            System.out.println(e);
        }
    }

}
