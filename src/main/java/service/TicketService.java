package service;

import busi.Ticket;
import dao.TicketDao;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TicketService {
    private TicketDao ticketDao;

    public TicketService(EntityManager manager) {
        this.ticketDao = new TicketDao(manager);
    }

    // Créer un nouveau ticket
    public void create(Ticket ticket) {
        ticketDao.save(ticket);
    }

    // Mettre à jour un ticket existant
    public void update(Ticket ticket) {
        ticketDao.update(ticket);
    }

    // Supprimer un ticket existant
    public void delete(Long ticketId) {
        ticketDao.delete(ticketId);
    }

    // Récupérer un ticket par son identifiant
    public Ticket getById(Long ticketId) {
        return ticketDao.getById(ticketId);
    }

    // Récupérer tous les tickets
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }
}
