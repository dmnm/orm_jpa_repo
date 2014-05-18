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
    public static void setUpBeforeClass() throws Exception {
        emf = Persistence.createEntityManagerFactory("default");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        emf.close();
    }

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void tearDown() throws Exception {
        em.getTransaction().commit();
        em.close();
    }

}