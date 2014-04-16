package orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.jpa.entity.Annotated;
import orm.jpa.entity.XMLMapping;

public class Main {

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("default");
    private static final EntityManager EM = EMF.createEntityManager();

    public static void main(final String[] args) {
        try {
            // first();
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

        final Annotated a = new Annotated();
        a.setName("a1");
        a.setValue("value");

        EM.persist(a);

        final XMLMapping xml = new XMLMapping();
        xml.name = "xml";
        xml.value = "value";

        EM.persist(xml);

        // yet another xml entity?
        xml.value = "value2";
        EM.persist(xml);

        EM.getTransaction().commit();

        EM.close();
    }

}
