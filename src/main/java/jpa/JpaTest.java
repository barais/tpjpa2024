package jpa;

import dao.DoctorDAO;
import dao.PatientDAO;
import dao.SpecialisationDAO;
import entities.Doctor;
import entities.Patient;
import entities.Specialisation;

import javax.persistence.EntityManager;
import java.io.PrintWriter;
import java.util.List;

public class JpaTest {

	private EntityManager manager;
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Specialisation dentist = new Specialisation("Dentist");
		Doctor dentist1 = new Doctor("Justine", "DELOURMEL", dentist);
		Patient patient1 = new Patient("Arnaud", "DELOURMEL", 1236546L);

		SpecialisationDAO specialisationDAO = new SpecialisationDAO();
		specialisationDAO.save(dentist);

		DoctorDAO doctorDAO = new DoctorDAO();
		doctorDAO.save(dentist1);

		PatientDAO patientDAO = new PatientDAO();
		patientDAO.save(patient1);
	}


	private void createDoctors() {
		int numOfDoctors = manager.createQuery("Select a From Doctor a", Doctor.class).getResultList().size();
		if (numOfDoctors == 0) {
			Specialisation specialisation = new Specialisation("Dentist");
			manager.persist(specialisation);
			manager.persist(new Doctor("Justine", "DELOURMEL",specialisation));
			manager.persist(new Patient("Arnaud", "DELOURMEL", 123454324L));
			manager.persist(new Doctor("Captain", "Nemo",specialisation));
		}
	}

	private void listDoctors(PrintWriter print) {
		List<Doctor> resultList = manager.createQuery("Select a From Doctor a", Doctor.class).getResultList();
		print.println("num of doctors:" + resultList.size());
		for (Doctor next : resultList) {
			print.println("next doctor: " + next);
		}
	}


}
