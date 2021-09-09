package jpa;

import model.Profs;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();


		try {
			Profs arnaud = new Profs();
			arnaud.setName("Arnaud");
			Profs yao = new Profs();
			yao.setName("Yao");

			manager.persist(arnaud);
			manager.persist(yao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}


}
