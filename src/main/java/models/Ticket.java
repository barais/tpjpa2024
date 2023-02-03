package models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
public class Ticket {
    private Long id;
    private String numero;
    private String titre;
    private Calendar createdAt;
    private Integer etat;
    private Set<Label> labels;
    private Utilisateur utilisateur;
    private Gestionnaire gestionnaire;
    private Set<Commentaire> commentaires;

    public Ticket() {}

    public Ticket(String numero, String titre, Calendar createdAt, Integer etat, Set<Label> labels, Utilisateur user, Gestionnaire gestionnaire) {
        this.numero = numero;
        this.titre = titre;
        this.createdAt = createdAt;
        this.etat = etat;
        this.labels = labels;
        this.utilisateur = user;
        this.gestionnaire = gestionnaire;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Column(unique = true)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    @ManyToOne
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur user) {
        this.utilisateur = user;
    }

    @ManyToOne
    public Gestionnaire getGestionnaire() {
        return gestionnaire;
    }

    public void setGestionnaire(Gestionnaire gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    @OneToMany(mappedBy = "ticket")
    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
