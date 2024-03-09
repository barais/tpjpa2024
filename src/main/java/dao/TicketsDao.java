package dao;

import domaine.Tickets;

public class TicketsDao extends AbstractJpaDao<Long, Tickets> {
    public TicketsDao(){
        this.setClazz(Tickets.class);
    }
}

