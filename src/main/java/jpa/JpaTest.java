package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JpaTest {


	private final EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			// Création d'un concert + 2 tickets
			Concert concert = test.createConcert();
			concert.setTickets(List.of(
					test.createTicket("A001", 42.0d, concert),
					test.createTicket("E450", 37.5d, concert)
			));
			manager.persist(concert);

			// Création utilisateurs
			manager.persist(test.createAdministrateur());
			manager.persist(test.createOrganisateur());
			manager.persist(test.createUtilisateur());

			System.out.println("---- LISTE DES CONCERTS ----");
			System.out.println(test.listConcert());
			System.out.println("---- LISTE DES PERSONNES ----");
			System.out.println(test.listPersonnes());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		tx.commit();


   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	private Utilisateur createUtilisateur() {
		Utilisateur util = new Utilisateur();
		util.setNom("DUPONT");
		util.setPrenom("Michel");
		util.setDateNaissance(LocalDate.of(1990, 1, 1));
		util.setEmail("michel.dupont@yopmail.com");
		util.setDateInscription(LocalDate.now());
		util.setCreditCompte(45.0d);
		util.setPreferenceNotificationEmail(false);
		util.setPreferenceNotificationPush(true);
		return util;
	}

	private Administrateur createAdministrateur() {
		Administrateur admin = new Administrateur();
		admin.setNom("LECHEF");
		admin.setPrenom("Baptiste");
		admin.setDateNaissance(LocalDate.of(1980, 7, 25));
		admin.setEmail("baptiste.lechef@yopmail.com");

		admin.setActif(true);
		admin.setDateNomination(LocalDate.of(2025, 12, 31));
		return admin;
	}

	private Organisateur createOrganisateur() {
		Organisateur orga = new Organisateur();
		orga.setNom("COMBOURG");
		orga.setPrenom("Adeline");
		orga.setDateNaissance(LocalDate.of(1990, 3, 17));
		orga.setEmail("adeline.combourg2@yopmail.com");

		orga.setActif(true);
		orga.setNomStructure("Rock en scène");
		orga.setNumeroSiret("523 299 410 00531");
		orga.setAdresseSiege("18, chemin de Faivre, 89731 AUBERT");
		return orga;
	}

	private Concert createConcert() {
		Concert concert = new Concert();
		concert.setArtiste("ARTISTE");
		concert.setCapacite(2500L);
		concert.setDate(LocalDateTime.now());
		concert.setPopularite(3.5f);
		concert.setGenre("VARIETE");
		concert.setDescription("Super concert!");
		return concert;
	}

	private Ticket createTicket(String numeroPlace, Double prixUnitaire, Concert concert) {
		Ticket ticket = new Ticket("A001", 42.0d, concert);
		ticket.setNumeroPlace(numeroPlace);
		ticket.setPrixUnitaire(prixUnitaire);
		ticket.setStatut(StatutTicketEnum.ACHETE);
		ticket.setDateAchat(LocalDateTime.now());
		return ticket;
	}

	private List<Concert> listConcert() {
        return manager
				.createQuery("select c from Concert c", Concert.class)
				.getResultList();
	}

	private List<Personne> listPersonnes() {
		return manager
				.createQuery("select p from Personne p", Personne.class)
				.getResultList();
	}

}
