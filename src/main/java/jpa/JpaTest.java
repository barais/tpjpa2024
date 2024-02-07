package jpa;

import busi.*;
import service.*;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			// TODO create and persist entity
			// Création d'un nouveau service de gestion de tickets
			TicketService ticketService = new TicketService();

			// Création d'un nouveau ticket
			Ticket ticket1 = new Ticket("Problème de connexion", "Je ne peux pas me connecter à mon compte.");
			ticketService.createTicket(ticket1);

			// Mise à jour d'un ticket existant
			Ticket ticketToUpdate = ticketService.getTicketById(1L);
			ticketToUpdate.setDescription("Description mise à jour du ticket");
			ticketService.updateTicket(ticketToUpdate);

			// Suppression d'un ticket existant
			ticketService.deleteTicket(2L);

			// Récupération de tous les tickets
			List<Ticket> tickets = ticketService.getAllTickets();
			for (Ticket ticket : tickets) {
				System.out.println("Titre: " + ticket.getTitre() + ", Description: " + ticket.getDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}
}
