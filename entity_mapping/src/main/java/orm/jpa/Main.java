package orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.jpa.entity.Annotated;
import orm.jpa.entity.XMLMapping;

public class Main {

    public static void main(final String[] args) {
        try {
            final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            final EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            final Annotated a = new Annotated();
            a.setName("a1");
            a.setValue("value");

            em.persist(a);

            final XMLMapping xml = new XMLMapping();
            xml.name = "xml";
            xml.value = "value";

            em.persist(xml);

            // yet another xml entity?
            xml.value = "value2";
            em.persist(xml);

            em.getTransaction().commit();

            em.close();
            emf.close();
        } catch (final Exception e) {
            System.out.println(e);
        }
    }

}
