package orm.jpa.first;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.internal.jpa.ExceptionFactory;

import orm.jpa.first.entity.FirstEntity;

public class Main {

	public static void main(String[] args) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();
	
			em.getTransaction().begin();
			
			FirstEntity fe = new FirstEntity();
			fe.setName("First");
			fe.setAmount(1L);
			
			em.persist(fe);
			
			em.getTransaction().commit();
			
			em.getTransaction().begin();
	
			Query query = em.createQuery("SELECT e FROM FirstEntity e WHERE e.name = 'First'");
			List<FirstEntity> results = query.getResultList();
	
	
			for (FirstEntity entity : results) {
				System.out.println(entity.getId() + " :: " + entity.getAmount());
				entity.setAmount(entity.getAmount() * 2);
				System.out.println(entity.getId() + " :: " + entity.getAmount());
			}
	
			em.getTransaction().commit();
	
			em.close();
			emf.close();
		} catch (Exception e){
			System.out.println(e);
		}
	}

}
