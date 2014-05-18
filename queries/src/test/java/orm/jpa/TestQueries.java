package orm.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
import orm.jpa.entity.Programmer;
import orm.jpa.entity.Programmer_;

public class TestQueries extends TestCasesJpa {

    @Test
    public void testJpql() {
        final String ql = "select " +
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
    public void testNative() {}
    
    @Test
    public void testNamed() {
        final Query countQuery = em.createNamedQuery("Departments.count");
        final long count = (long) countQuery.getSingleResult();

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
    public void testCriteriaApi() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Programmer> criteria = builder.createQuery(Programmer.class);
        final Root<Programmer> root = criteria.from(Programmer.class);

        final ParameterExpression<String> name = builder.parameter(String.class);
        final ParameterExpression<String> lang = builder.parameter(String.class);

        final Predicate nameCondition = builder.equal(root.get(Programmer_.firstName), name);
        final Predicate langCondition = builder.equal(root.get(Programmer_.primaryLanguage), lang);
        final Predicate conditions = builder.or(nameCondition, langCondition);

        criteria.where(conditions);

        final TypedQuery<Programmer> query = em.createQuery(criteria);
        query.setParameter(name, "some_name");
        query.setParameter(lang, "java");

        final List<Programmer> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("frst", result.get(0).firstName);
    }

    @Test
    public void testNotCriteriaApi() {
        final String ql = 
                "select p from Programmer p " +
        		"where p.firstName = :firstName" +
        		" or p.primaryLanguage = :primaryLanguage";
        final Query query = em.createQuery(ql);

        query.setParameter("firstName", "some_name");
        query.setParameter("primaryLanguage", "java");

        final List<Programmer> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("frst", result.get(0).firstName);
    }
}
