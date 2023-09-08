package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.CRUD.EmployeeCRUD;

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

		EmployeeCRUD crud = new EmployeeCRUD(manager);

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
			System.out.println("________________________________________________________");
			System.out.println("Departments après initialisation :");
			crud.printDepartments();
			System.out.println("________________________________________________________");

			// Affiche les employés
			System.out.println("________________________________________________________");
			System.out.println("Employees après initialisation :");
			crud.printEmployees();
			System.out.println("________________________________________________________");

			// Peuple la table avec des nouvelles valeur non persistées
			crud.createDepartment("Sales_tmp", false);
			crud.createDepartment("Development_tmp", false);
			crud.createDepartment("Research_tmp", false);

			crud.createEmployee("John Doe_tmp", "Sales_tmp", false);

			crud.createEmployee("Jack Doe_tmp", "Development_tmp", false);

			crud.createEmployee("Jim Doe_tmp", "Research_tmp", false);

			// Affiche les départements
			System.out.println("________________________________________________________");
			System.out.println("Departments :");
			crud.printDepartments();
			System.out.println("________________________________________________________");

			// Affiche les employés
			System.out.println("________________________________________________________");
			System.out.println("Employees :");
			crud.printEmployees();
			System.out.println("________________________________________________________");

			// Met à jour un employé
			crud.updateEmployeeName("John Doe", "John Doe_updated");
			crud.updateEmployeeName("ceci n'est pas un nom", "Jack Doe_updated"); // ne devrait pas modifier d'employé

			// Affiche les employés
			System.out.println("________________________________________________________");
			System.out.println("Employees après mise à jour :");
			crud.printEmployees();
			System.out.println("________________________________________________________");

			// Supprime un employé
			crud.deleteEmployee("Jack Doe");
			crud.deleteEmployee("John Doe"); // ne devrait pas supprimer d'employé
			crud.deleteEmployee("ceci n'est pas un nom"); // ne devrait pas supprimer d'employé

			// Affiche les employés
			System.out.println("________________________________________________________");
			System.out.println("Employees après suppression :");
			crud.printEmployees();
			System.out.println("________________________________________________________");

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}




}
