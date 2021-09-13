package jpa;

import dao.*;

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
		//EntityManagerFactory factory = Persistence
		//				.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();

		ProfessionnelDao professionnelDao = new ProfessionnelDao(manager);
		DepartementDao departementDao = new DepartementDao(manager);
		UtilisateurDao utilisateurDao = new UtilisateurDao(manager);
		RdvDao rdvDao = new RdvDao(manager);
		PersonneDao personneDao = new PersonneDao(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			departementDao.createDepartements();

			professionnelDao.createProfessionnels();

			utilisateurDao.createUtilisateurs();

			rdvDao.createRdvs();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		professionnelDao.listProfessionnelsParNom("Prof");
		professionnelDao.listProfessionnels();
		departementDao.listDepartements();
		utilisateurDao.printlistUtilisateurs();
		rdvDao.listRdvs();
		personneDao.listPersonnes();

		manager.close();
		factory.close();
	}

}
