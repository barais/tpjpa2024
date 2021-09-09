package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Rdv {
    private Long id;

    private String nom;

    private int dureeMinimale;

    private Professionnel professionnel;

    public Rdv() {
    }

    public Rdv(String nom, int dureeMinimale, Professionnel professionnel) {
        this.nom = nom;
        this.dureeMinimale = dureeMinimale;
        this.professionnel = professionnel;
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

    public int getDureeMinimale(){return dureeMinimale;}

    public void setDureeMinimale(int dureeMinimale) {this.dureeMinimale = dureeMinimale;}

    @ManyToOne
    public Professionnel getProfessionnel() {
        return professionnel;
    }

    public void setProfessionnel(Professionnel professionnel) {
        this.professionnel = professionnel;
    }

    @Override
    public String toString() {
        return "RDV [id=" + id + ", nom=" + nom + "]";
    }
}
