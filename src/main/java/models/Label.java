package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Label implements Serializable {

    private Long id;
    private String nom;
    private String description;
    private String couleur;
    private List<Ticket> tickets;

    public Label() {}

    public Label(String nom, String description, String couleur) {
        this.nom = nom;
        this.description = description;
        this.couleur = couleur;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Column(unique = true, nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @ManyToMany(mappedBy = "labels", cascade = CascadeType.PERSIST)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Label{nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", couleur='" + couleur + '\'' +
                '}';
    }
}
