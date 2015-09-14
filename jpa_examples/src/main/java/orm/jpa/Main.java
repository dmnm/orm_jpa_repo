package orm.jpa;

import static java.util.Arrays.asList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.jpa.entity.Company;
import orm.jpa.entity.Department;
import orm.jpa.entity.Employee;
import orm.jpa.entity.Project;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = emf.createEntityManager();

    public static void main(final String[] args) {
        try {
            fill();
            //testLifecycle();
        } catch (final Exception e) {
            System.err.println(e);
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void fill() {
        em.getTransaction().begin();

        {
            final Employee pj = new Employee();
            pj.setFirstName("Java");
            pj.setSecondName("Developer");

            em.persist(pj);
        }

        {
            final Department sqa = new Department();
            sqa.setName("SQA");

            em.persist(sqa);

            final Employee t = new Employee();
            t.setFirstName("Tester");

            t.setDepartment(sqa);

            em.persist(t);
        }

        {
            final Employee me = new Employee();
            me.setFirstName("Dmitry");
            me.setSecondName("Popov");

            final Department department = new Department();
            department.setName("R&D");

            me.setDepartment(department);

            final Project project = new Project();
            project.setName("Hugin ODC");

            me.setProjects(asList(project));
            project.setEmployees(asList(me));

            em.persist(me);
        }

        em.getTransaction().commit();
    }

    @SuppressWarnings("unused")
    private static void testLifecycle() {
        em.getTransaction().begin();

        final Company company = new Company();
        company.setName("StarSoft");
        company.setHome("US");

        em.persist(company);

        company.setName("Exigen");
        em.persist(company);

        em.getTransaction().commit();

        company.setName("ROI");
        em.persist(company);

        company.setName("Return on Intelligence");
        em.merge(company);
    }
}
