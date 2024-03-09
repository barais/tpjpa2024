package jpa;


import domaine.Projects;
import domaine.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import utils.Role;

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
			test.createProjects();
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
			manager.persist(new Utilisateur("user3", "Jakab Owner", Role.OWNER));
			manager.persist(new Utilisateur("user4","Captain Reporter", Role.REPORTER));
			manager.persist(new Utilisateur("user5", "Jakab DEV2", Role.DEVELOPER));
			manager.persist(new Utilisateur("user6","Captain DEV2", Role.DEVELOPER));
		}
	}

	private void createProjects() {
		int numOfProjects = manager.createQuery("Select a From Projects a", Projects.class).getResultList().size();
		if (numOfProjects == 0) {
			manager.persist(new Projects("SIR Backend"));
			manager.persist(new Projects("SIR Frontend"));
		}
	}


}
