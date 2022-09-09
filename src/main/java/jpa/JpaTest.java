package jpa;

import entities.Doctor;
import entities.Specialisation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		JpaTest test = new JpaTest(manager);


		try {
			test.createDoctor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}


	private void createDoctor() {
		int numOfDoctors = manager.createQuery("Select a From Doctor a", Doctor.class).getResultList().size();
		if (numOfDoctors == 0) {
			Specialisation specialisation = new Specialisation("Dentist");
			manager.persist(specialisation);
			manager.persist(new Doctor("Jakab", "Gipsz",specialisation));
			manager.persist(new Doctor("Captain", "Nemo",specialisation));
		}
	}
	private void listDoctors() {
		List<Doctor> resultList = manager.createQuery("Select a From Doctor a", Doctor.class).getResultList();
		System.out.println("num of doctors:" + resultList.size());
		for (Doctor next : resultList) {
			System.out.println("next doctor: " + next);
		}
	}


}
