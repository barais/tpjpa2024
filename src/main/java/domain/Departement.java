package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departement {
    private Long id;

    private String nom;

    private List<Professionnel> professionnels = new ArrayList<Professionnel>();

    public Departement() {
    }

    public Departement(String name) {
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

    @Override
    public String toString() {
        return "Departement [id=" + id + ", name=" + nom + "]";
    }


    @OneToMany(mappedBy = "departement", cascade = CascadeType.PERSIST)
    public List<Professionnel> getProfessionnels() {
        return professionnels;
    }

    public void setProfessionnels(List<Professionnel> professionnels) {
        this.professionnels = professionnels;
    }


}