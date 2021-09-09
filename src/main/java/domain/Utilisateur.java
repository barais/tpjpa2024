package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Utilisateur {
    private Long id;

    private String nom;

    private List<Rdv> rdvs = new ArrayList<>();

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


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.PERSIST)
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(List<Rdv> rdvs){
        this.rdvs = rdvs;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + "]";
    }
}
