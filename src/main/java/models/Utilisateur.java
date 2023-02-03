package models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Inheritance
public class Utilisateur {
    protected Long id;
    protected String nom;
    protected String prenoms;
    protected String username;
    protected String password;
    protected Calendar createdAt;
    private Set<Ticket> tickets;

    public Utilisateur() {}

    public Utilisateur(String nom, String prenoms, String username, String password, Calendar createdAt) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

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

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.PERSIST)
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
