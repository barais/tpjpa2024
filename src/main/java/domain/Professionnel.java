package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Professionnel {
    private Long id;

    private String nom;

    private Departement departement;

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
    public Departement getDepartment() {
        return departement;
    }

    public void setDepartment(Departement department) {
        this.departement = department;
    }

    @Override
    public String toString() {
        return "Professionnel [id=" + id + ", nom=" + nom + ", departement="
                + departement.getName() + "]";
    }

}

