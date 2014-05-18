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

import orm.jpa.entity.Programmer;
import orm.jpa.entity.Programmer_;

public class TestQueries extends TestCasesJpa {

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
        final String ql = "select p from Programmer p where p.firstName = :firstName or p.primaryLanguage = :primaryLanguage";
        final Query query = em.createQuery(ql);

        query.setParameter("firstName", "some_name");
        query.setParameter("primaryLanguage", "java");

        final List<Programmer> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("frst", result.get(0).firstName);
    }
}
