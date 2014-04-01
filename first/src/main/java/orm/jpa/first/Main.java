package orm.jpa.first;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import orm.jpa.first.entity.FirstEntity;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Query query = em.createQuery("SELECT e FROM FirstEntity e WHERE e.name = 'First'");
		List<FirstEntity> results = query.getResultList();


		for (FirstEntity entity : results) {
			entity.setAmount(entity.getAmount() * 2);
		}

		em.getTransaction().commit();

		em.close();
	}

}
