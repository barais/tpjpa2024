package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Rdv {
    private Long id;

    private String name;

    private int dureeMinimale;

    private Professionnel professionnel;

    public Rdv() {
    }

    public Rdv(String name, int dureeMinimale, Professionnel professionnel) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "RDV [id=" + id + ", name=" + name + "]";
    }
}
