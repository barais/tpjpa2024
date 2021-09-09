package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Utilisateur {
    private Long id;

    private String nom;

    public Utilisateur() {
    }

    public Utilisateur (String nom) {
        this.nom = nom;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + "]";
    }
}
