package jpa;


import domain.Department;
import domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

		JpaCRUD crud = new JpaCRUD(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			// Peuple la table avec des nouvelles valeur

			crud.createDepartment("Sales", true);
			crud.createDepartment("Development", true);
			crud.createDepartment("Research", true);
			crud.createDepartment("Marketing", true);

			crud.createEmployee("John Doe", "Sales", true);
			crud.createEmployee("Jane Doe", "Sales", true);

			crud.createEmployee("Jack Doe", "Development", true);

			crud.createEmployee("Jim Doe", "Research", true);
			crud.createEmployee("Jill Doe", "Research", true);

			crud.createEmployee("Jerry Doe", "Marketing", true);
			crud.createEmployee("Janet Doe", "Marketing", true);

			// Affiche les départements

			crud.printDepartments();
			// Affiche les employés
			crud.printEmployees();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}




}
