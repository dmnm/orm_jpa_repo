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
            //testMerge();
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
            pj.firstName = "Java";
            pj.secondName = "Developer";

            em.persist(pj);
        }

        {
            final Department sqa = new Department();
            sqa.name = "SQA";

            em.persist(sqa);

            final Employee t = new Employee();
            t.firstName = "Tester";

            t.department = sqa;

            em.persist(t);
        }

        {
            final Employee me = new Employee();
            me.firstName = "Dmitry";
            me.secondName = "Popov";

            final Department department = new Department();
            department.name = "R&D";

            me.department = department;

            final Project project = new Project();
            project.name = "Hugin";

            me.projects = asList(project);
            project.employees = asList(me);

            em.persist(me);
        }

        em.getTransaction().commit();
    }

    private static void testMerge() {
        em.getTransaction().begin();

        final Company company = new Company();
        company.name = "Exigen";
        company.home = "US";

        em.persist(company);

        company.name = "ROI";
        em.persist(company);

        em.getTransaction().commit();

        company.name = "ROI";
        em.persist(company);

        company.name = "Return on Intelligence";
        em.merge(company);
    }
}
