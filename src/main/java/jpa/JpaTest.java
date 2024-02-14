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
		this.userService = new UtilisateurService();
		this.roleService = new RoleService();
		this.ticketService = new TicketService();
		this.tagService = new TagService();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		try {
			//test.createData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		/*private void createData() {
			Role supportRole = roleService.createRole("support");
			User user1 = utilisateurService.createUser("User 1", supportRole);
			User user2 = utilisateurService.createUser("User 2", supportRole);
			Tag tag = tagService.createTag(new Tag("tag 1"));

			// Créer un ticket
			Ticket ticket = new Ticket();
			ticket.setTitle("Un ticket");
			ticket.setDescritpion("Lorem ipsum machin truc");
			ticket.setPriority(PriorityEnum.ONE);
			ticket.setState(StateEnum.OUVERT);
			ticket.setCreatedBy(user1);
			ticket.setCreatedAt(ZonedDateTime.now());
			ticket.getTags().add(tag);
			ticketService.create(ticket);

			// Attribuer le ticket à l'autre utilisateur
			ticket.setAssignedTo(user2);
			ticketService.update(ticket);
		}*/
		tx.commit();

			
   	    manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}
}
