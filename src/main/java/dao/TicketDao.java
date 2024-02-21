package dao;

import busi.Ticket;
import jakarta.persistence.EntityManager;
import java.util.List;
public class TicketDao {
    private final EntityManager entityManager;
    public TicketDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Ticket ticket) {
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
    }
    public void update(Ticket ticket) {
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
    }
    public void delete(Long ticketId) {
        Ticket ticket = getById(ticketId);
        if (ticket != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(ticket);
            entityManager.getTransaction().commit();
        }
    }
    public Ticket getById(Long ticketId) {
        return entityManager.find(Ticket.class, ticketId);
    }
    public List<Ticket> getAllTickets() {
        return entityManager.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
    }
}

