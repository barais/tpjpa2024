package jpa;

import dao.ProfessionnelDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();


		ProfessionnelDao profdao = new ProfessionnelDao(manager);
		profdao.createProfessionnels();

		manager.close();
		factory.close();
	}

}
