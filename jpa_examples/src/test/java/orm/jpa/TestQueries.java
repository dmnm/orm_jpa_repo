package orm.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;

import orm.jpa.entity.Department;
import orm.jpa.entity.Employee;
import orm.jpa.entity.Employee_;
import orm.jpa.entity.Project;

public class TestQueries extends TestCasesJpa {

    @Test
    public void test_native() {
        final String sql =
                "select e.id, e.firstname, e.secondname, d.name, p.name" +
                        " from employee e" +
                        " join department d on d.id = e.department_id" +
                        " join project_has_employee pe on pe.employee_id = e.id" +
                        " inner join project p on p.id = pe.project_id" +
                        " where e.firstname = ?1" +
                        " and e.secondname = ?2" +
                        " and p.name = ?3" +
                        " and d.name = ?4";

        final Query query = em.createNativeQuery(sql)
                .setParameter(1, "Dmitry")
                .setParameter(2, "Popov")
                .setParameter(3, "Hugin")
                .setParameter(4, "R&D");

        final List<Object[]> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).length);

        for (final Object o : result.get(0)) {
            System.err.println(o);
        }
    }

    @Test
    public void test_jpql_simple() {
        final String jpql =
                "select e, d, p from Employee e" +
                        " join e.department d" +
                        " join e.projects p" +
                        " where e.firstName = :firstName" +
                        " and e.secondName = :secondName" +
                        " and p.name = :project" +
                        " and d.name = :department";

        final Query query = em.createQuery(jpql)
                .setParameter("firstName", "Dmitry")
                .setParameter("secondName", "Popov")
                .setParameter("project", "Hugin")
                .setParameter("department", "R&D");

        final List<Object[]> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).length);

        assertTrue(result.get(0)[0] instanceof Employee);
        assertTrue(result.get(0)[1] instanceof Department);
        assertTrue(result.get(0)[2] instanceof Project);

        System.err.println(result.get(0)[0]);
        System.err.println(result.get(0)[1]);
        System.err.println(result.get(0)[2]);
    }

    @Test
    public void test_jpql_with_dto() {
        final String jpql =
                "select new orm.jpa.EmployeeDto(" +
                        " e.id, " +
                        " e.firstName, " +
                        " e.secondName, " +
                        " e.department.name, " +
                        " p.name" +
                        ") " +
                        "from Project p join p.employees e " +
                        "where e.firstName = :firstName and p.name = :project";

        final Query query = em.createQuery(jpql)
                .setParameter("firstName", "Dmitry")
                .setParameter("project", "Hugin");

        query.setFirstResult(0);
        query.setMaxResults(10);

        final List<EmployeeDto> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        for (final EmployeeDto dto : result) {
            System.err.println(dto);
        }
    }

    @Test
    public void test_named() {
        final Query countQuery = em.createNamedQuery("Departments.count");
        final long count = (Long) countQuery.getSingleResult();

        final Query allQuery = em.createNamedQuery("Departments.all");
        final List<Department> all = allQuery.getResultList();

        final Query byNameQuery = em.createNamedQuery("Departments.byName").setParameter("name", "SQA");
        final List<Department> byName = byNameQuery.getResultList();

        assertEquals(2L, count);
        System.err.println("Departments.count: " + count);

        assertFalse(all.isEmpty());
        assertEquals(2, all.size());

        System.err.println("Departments.all: {");
        for (final Department d : all) {
            System.err.println("\t" + d);
        }
        System.err.println("}");

        assertFalse(byName.isEmpty());
        assertEquals(1, byName.size());
        assertEquals("SQA", byName.get(0).name);

        System.err.println("Departments.byName = SQA: {");
        for (final Department d : byName) {
            System.err.println("\t" + d);
        }
        System.err.println("}");
    }

    @Test
    public void test_criteria_api() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        final Root<Employee> root = criteria.from(Employee.class);

        final ParameterExpression<String> first_name = builder.parameter(String.class);
        final ParameterExpression<String> second_name = builder.parameter(String.class);

        final Predicate fnameCondition = builder.equal(root.get(Employee_.firstName), first_name);
        final Predicate snameCondition = builder.equal(root.get(Employee_.secondName), second_name);
        final Predicate conditions = builder.and(fnameCondition, snameCondition);

        criteria.where(conditions);

        final TypedQuery<Employee> query = em.createQuery(criteria)
                .setParameter(first_name, "Dmitry")
                .setParameter(second_name, "Popov");

        final List<Employee> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Dmitry", result.get(0).firstName);

        System.err.println(result.get(0));
    }

    @Test
    public void test_no_criteria_api() {
        final String jpql =
                "select e from Employee e" +
                        " where e.firstName = :firstName" +
                        " and e.secondName = :secondName";

        final Query query = em.createQuery(jpql)
                .setParameter("firstName", "Dmitry")
                .setParameter("secondName", "Popov");

        final List<Employee> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Dmitry", result.get(0).firstName);

        System.err.println(result.get(0));
    }
}
