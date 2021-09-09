package jpa;

import dao.DepartementDao;
import dao.ProfessionnelDao;
import dao.RdvDao;
import dao.UtilisateurDao;

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

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			DepartementDao departementDao = new DepartementDao(manager);
			departementDao.createDepartements();

			ProfessionnelDao professionnelDao = new ProfessionnelDao(manager);
			professionnelDao.createProfessionnels();

			RdvDao rdvDao = new RdvDao(manager);
			rdvDao.createRdvs();

			UtilisateurDao utilisateurDao = new UtilisateurDao(manager);
			utilisateurDao.createUtilisateurs();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		manager.close();
		factory.close();
	}

}
