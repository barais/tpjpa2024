package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("P")
@NamedQueries(
        {
                @NamedQuery(name="tousLesProfessionnelsParNom", query="SELECT p FROM Professionnel p WHERE p.nom LIKE CONCAT('%',:name,'%') ORDER BY p.nom")
        }
)
public class Professionnel extends Personne{

    private Departement departement;

    private List<Rdv> rdvs = new ArrayList<>();

    public Professionnel() {
    }

    public Professionnel(String name, Departement department) {
        super(name);
        this.departement = department;
    }

    @ManyToOne
    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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
        return "Professionnel [id=" + getId() + ", nom=" + getNom() + ", prénom=" + getPrenom() + ", département="
                + departement.getNom() + "]";
    }

}

