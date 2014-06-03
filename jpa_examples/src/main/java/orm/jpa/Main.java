package orm.jpa;

import static java.util.Arrays.asList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import orm.jpa.entity.Department;
import orm.jpa.entity.Employee;
import orm.jpa.entity.Project;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = emf.createEntityManager();

    public static void main(final String[] args) {
        try {
            fill();
        } catch (final Exception e) {
            System.out.println(e);
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void fill() {
        em.getTransaction().begin();

        {
            final Employee pj = new Employee();
            pj.firstName = "Jave";
            pj.secondName = "Developer";

            em.persist(pj);
        }

        {
            final Employee t = new Employee();
            t.firstName = "Tester";

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

        {
            final Department sqa = new Department();
            sqa.name = "SQA";

            em.persist(sqa);
        }

        em.getTransaction().commit();
    }

}
