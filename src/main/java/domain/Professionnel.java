package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Professionnel {
    private Long id;

    private String nom;

    private Departement departement;

    private List<Rdv> rdvs = new ArrayList<>();

    public Professionnel() {
    }

    public Professionnel(String name, Departement department) {
        this.nom = name;
        this.departement = department;
    }

    public Professionnel(String name) {
        this.nom = name;
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
        return nom;
    }

    public void setName(String name) {
        this.nom = name;
    }

    @ManyToOne
    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement department) {
        this.departement = department;
    }

    @OneToMany(mappedBy = "professionnel", cascade = CascadeType.PERSIST)
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(List<Rdv> rdvs){
        this.rdvs = rdvs;
    }

    @Override
    public String toString() {
        return "Professionnel [id=" + id + ", nom=" + nom + ", departement="
                + departement.getName() + "]";
    }

}

