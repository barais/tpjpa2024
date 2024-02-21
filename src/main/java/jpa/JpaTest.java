package jpa;

import busi.*;
import jakarta.persistence.*;
import service.TagService;
import service.TicketService;
import service.UtilisateurService;

import java.time.ZonedDateTime;
import java.util.HashSet;

public class JpaTest {

	private EntityManager manager;
	private UtilisateurService utilisateurService;
	private TicketService ticketService;
	private TagService tagService;


	public JpaTest(EntityManager manager) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		this.manager = entityManagerFactory.createEntityManager();
		this.utilisateurService = new UtilisateurService(this.manager);
		this.ticketService = new TicketService(this.manager);
		this.tagService = new TagService(this.manager);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		try {
			test.createData();
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

	private void createData() {
		// Création d'un utilisateur
		Utilisateur user = new Utilisateur();
		user.setNom("John Doe");
		user.setEmail("john.doe@example.com");
		user.setRole(Role.UTILISATEUR);
		utilisateurService.create(user);

		// Récupération d'un utilisateur par son identifiant
		Utilisateur retrievedUser = utilisateurService.getById(user.getId());
		System.out.println("Utilisateur récupéré : " + retrievedUser.getNom());

		// Création d'un ticket
		Ticket ticket = new Ticket();
		ticket.setTitle("Un ticket");
		ticket.setDescription("Lorem ipsum machin truc");
		ticket.setPriorite(Priorite.HAUTE);
		ticket.setStatut(Statut.EN_ATTENTE);
		ticket.setUtilisateur(retrievedUser);
		ticket.setCreatedAt(ZonedDateTime.now());

		// Initialisation de la liste de tags
		ticket.setTags(new HashSet<>());

		ticketService.create(ticket);

		// Création d'un tag
		Tag tag = new Tag("Tag Example");
		tagService.create(tag);

		// Ajout du tag au ticket
		ticket.getTags().add(tag);
		ticketService.update(ticket);
	}

}
