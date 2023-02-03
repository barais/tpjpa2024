package jpa;

import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaTest {

    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        JpaTest test = new JpaTest(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createTickets();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listTickets();

        manager.close();
        System.out.println(".. done");
    }

    private void createTickets() {
        int numOfTickets = manager.createQuery("Select t From Ticket t", Ticket.class).getResultList().size();
        if (numOfTickets == 0) {
            Label label = new Label("Bug", "Les bugs", "#0000000");
            Set<Label> labels = new HashSet<>();
            labels.add(label);
            Utilisateur utilisateur = new Utilisateur("Daté", "Arnaud", "adate", "12345", Calendar.getInstance());
            Ticket t = new Ticket("123", "Ticket 1", Calendar.getInstance(), EtatTicket.NOUVEAU,labels, null, null);
            Set<Ticket> tickets = new HashSet<>();
            tickets.add(t);
            utilisateur.setTickets(tickets);
            manager.persist(utilisateur);
        }
    }

    private void listTickets() {
        List<Ticket> resultList = manager.createQuery("Select t From Ticket t", Ticket.class).getResultList();
        System.out.println("num of tickets :" + resultList.size());
        for (Ticket next : resultList) {
            System.out.println("next ticket: " + next);
        }
    }
}



