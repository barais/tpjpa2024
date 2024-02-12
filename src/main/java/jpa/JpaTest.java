package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			// TODO create and persist entity
			test.createUtilisateurs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	private void createUtilisateurs() {
		int numOfUtilisateurs = manager.createQuery("Select a From Utilisateur a", Utilisateur.class).getResultList().size();
		if (numOfUtilisateurs == 0) {

			manager.persist(new Utilisateur("user1", "Jakab Gipsz", Role.DEVELOPER));
			manager.persist(new Utilisateur("user2","Captain Nemo", Role.MAINTENER));

		}
	}



}
