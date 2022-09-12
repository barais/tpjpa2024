package jpa;

import jpa.dao.ChildDAO;
import jpa.dao.PatientDAO;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		var manager = EntityManagerHelper.getEntityManager();
		var tx = manager.getTransaction();
		var patientDAO = new PatientDAO();
		var childDAO = new ChildDAO();
		tx.begin();
		try {
			patientDAO.createPatients("Cambria", "Alpha");
			patientDAO.createPatients("Maria", "Beta");
			patientDAO.createPatients("Jean", "Citron");
			childDAO.createChild("firstChild", "good", "parent", 12);

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
