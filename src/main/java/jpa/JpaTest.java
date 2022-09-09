package jpa;

import jpa.dao.PatientDAO;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		EntityManager manager = EntityManagerHelper.getEntityManager();
//		EntityTransaction tx = manager.getTransaction();
//		tx.begin();
//
//
//		try {
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		tx.commit();
//
//
//		manager.close();
//		EntityManagerHelper.closeEntityManagerFactory();
//		//		factory.close();

		var manager = EntityManagerHelper.getEntityManager();
		var tx = manager.getTransaction();
		var patientDAO = new PatientDAO();
		tx.begin();
		try {
			patientDAO.createPatients("Cambria", "Alpha");
			patientDAO.createPatients("Maria", "Beta");
			patientDAO.createPatients("Jean", "Citron");

			patientDAO.getPatientList().forEach(System.out::println);

			System.out.println(patientDAO.getPatientByName("Maria", "Beta"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		manager.close();
		System.out.println("done ...");
	}

}
