package orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestCasesJpa {

    protected static EntityManagerFactory emf;

    protected EntityManager em;

    @BeforeClass
    public static void beforeClass() throws Exception {
        emf = Persistence.createEntityManagerFactory("default");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        emf.close();
    }

    @Before
    public void beforeTest() throws Exception {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void afterTest() throws Exception {
        em.getTransaction().commit();
        em.close();
    }

}