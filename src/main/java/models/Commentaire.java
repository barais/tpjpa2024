package models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Commentaire {
    private Long id;
    private Calendar createdAt;
    private Calendar updateAt;
    private Ticket ticket;
    private Utilisateur utilisateur;

    public Commentaire() {}

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Calendar updateAt) {
        this.updateAt = updateAt;
    }

    @ManyToOne
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @ManyToOne
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
