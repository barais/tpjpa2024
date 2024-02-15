package service;

import busi.Ticket;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TicketService {
    private EntityManager manager;

    public TicketService(EntityManager manager) {
        this.manager = manager;
    }

    // Créer un nouveau ticket
    public void create(Ticket ticket) {
        manager.getTransaction().begin();
        manager.persist(ticket);
        manager.getTransaction().commit();
    }

    // Mettre à jour un ticket existant
    public void update(Ticket ticket) {
        manager.getTransaction().begin();
        manager.merge(ticket);
        manager.getTransaction().commit();
    }

    // Supprimer un ticket existant
    public void delete(Long ticketId) {
        manager.getTransaction().begin();
        Ticket ticket = manager.find(Ticket.class, ticketId);
        manager.remove(ticket);
        manager.getTransaction().commit();
    }

    // Récupérer un ticket par son identifiant
    public Ticket getById(Long ticketId) {
        return manager.find(Ticket.class, ticketId);
    }

    // Récupérer tous les tickets
    public List<Ticket> getAll() {
        return manager.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
    }
}
