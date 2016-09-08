package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Department;
import domain.Employee;

public class JpaTest {

	/**
	 * Classe de test pour peupler la base
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			Department d = new Department();
			d.setName("yolo");
			manager.persist(d);

			Employee e = new Employee();
			e.setName("John");
			e.setDepartment(d);
			manager.persist(e);
			
			String query ="select e from Department as e where e.name=:nom";
			List<Department> l = manager.createQuery(query).setParameter("nom", "yolo").getResultList();
			System.err.println(l.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		manager.close();
		factory.close();
	}

}
