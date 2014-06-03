package orm.jpa;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;

import orm.jpa.entity.Company;
import orm.jpa.entity.Department;
import orm.jpa.entity.Employee;
import orm.jpa.entity.Employee_;
import orm.jpa.entity.User;

public class TestQueries extends TestCasesJpa {

    @Test
    public void testJpql() {
        final String ql =
                "select " +
        		"new orm.jpa.EmpDto(" +
        		" e.id, " +
        		" e.firstName, " +
        		" e.secondName, " +
        		" e.department.name, " +
        		" p.name, " +
        		" type(e)" +
        		") " +
        		"from Project p join p.employees e " +
        		"where e.firstName = ?1 and p.name = ?2";

        final Query query = em.createQuery(ql);

        query.setParameter(1, "Dmitry");
        query.setParameter(2, "Hugin");

        query.setFirstResult(0);
        query.setMaxResults(10);

        final List<EmpDto> result = query.getResultList();

        for (final EmpDto dto : result) {
            System.err.println(dto);
        }

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testNamed() {
        final Query countQuery = em.createNamedQuery("Departments.count");
        final long count = (Long) countQuery.getSingleResult();

        final Query allQuery = em.createNamedQuery("Departments.all");
        final List<Department> all = allQuery.getResultList();

        final Query byNameQuery = em.createNamedQuery("Departments.byName").setParameter("name", "SQA");
        final List<Department> byName = byNameQuery.getResultList();

        assertEquals(2L, count);

        assertFalse(all.isEmpty());
        assertEquals(2, all.size());

        assertFalse(byName.isEmpty());
        assertEquals(1, byName.size());
        assertEquals("SQA", byName.get(0).name);
    }

    @Test
    public void testNative() {
        final String sql = 
                "select d.id, d.name, count(e.id) as count_of_employees " +
                "from department d " +
                "left join employee e on e.department_id = d.id " +
                "group by d.id";
        final Query query = em.createNativeQuery(sql, "departmentsWithCountOfEmployees");

        final List<Object[]> result = query.getResultList();

        for(final Object[] array : result) {
            assertTrue(array[0] instanceof Department);
            assertTrue(array[1] instanceof Number);
            final Department d = (Department) array[0];
            System.err.println(d.name + ": " + array[1]);
        }
    }

    @Test
    public void testCriteriaApi() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        final Root<Employee> root = criteria.from(Employee.class);

        final ParameterExpression<String> first_name = builder.parameter(String.class);
        final ParameterExpression<String> second_name = builder.parameter(String.class);

        final Predicate nameCondition = builder.equal(root.get(Employee_.firstName), first_name);
        final Predicate langCondition = builder.equal(root.get(Employee_.secondName), second_name);
        final Predicate conditions = builder.or(nameCondition, langCondition);

        criteria.where(conditions);

        final TypedQuery<Employee> query = em.createQuery(criteria);
        query.setParameter(first_name, "some_name");
        query.setParameter(second_name, "java");

        final List<Employee> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("frst", result.get(0).firstName);
    }

    @Test
    public void testNotCriteriaApi() {
        final String ql = 
                "select p from Employee p " +
        		"where p.firstName = :firstName" +
        		" or p.primaryLanguage = :primaryLanguage";
        final Query query = em.createQuery(ql);

        query.setParameter("firstName", "some_name");
        query.setParameter("primaryLanguage", "java");

        final List<Employee> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("frst", result.get(0).firstName);
    }
    
    @Test
    public void testPersist() {

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
    }
}
