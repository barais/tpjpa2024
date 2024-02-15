package jpa;

import busi.*;
import service.*;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class JpaTest {

	private EntityManager manager;
	private UtilisateurService userService;
	private TicketService ticketService;
	private RoleService roleService;
	private TagService tagService;


	public JpaTest(EntityManager manager) {
		this.manager = manager;
		this.userService = new UtilisateurService(manager);
		this.roleService = new RoleService(manager);
		this.ticketService = new TicketService(manager);
		this.tagService = new TagService(manager);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		tx.commit();
   	    manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}
}
