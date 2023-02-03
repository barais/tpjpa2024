package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Gestionnaire extends Utilisateur {
    private String role;
    private Set<Ticket> ticketsAttribues;

    public Gestionnaire() {
        super();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "gestionnaire")
    public Set<Ticket> getTicketsAttribues() {
        return ticketsAttribues;
    }

    public void setTicketsAttribues(Set<Ticket> tickets) {
        this.ticketsAttribues = tickets;
    }
}
