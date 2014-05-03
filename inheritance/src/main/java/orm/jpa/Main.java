package orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.jpa.entity.Programmer;
import orm.jpa.entity.Tester;

public class Main {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("default");
    private static final EntityManager EM = EMF.createEntityManager();

    public static void main(final String[] args) {
        try {
            first();
        } catch (final Exception e) {
            System.out.println(e);
        } finally {
            EM.close();
            EMF.close();
        }
    }

    private static void first() {
        EM.getTransaction().begin();

        final Programmer pj = new Programmer();
        pj.firstName = "frst";
        pj.secondName = "scnd";
        pj.primaryLanguage = "java";
        pj.secondaryLanguage = "js";

        EM.persist(pj);

        final Tester t = new Tester();
        t.firstName = "name";
        t.automation = true;

        EM.persist(t);

        EM.getTransaction().commit();
    }

}
