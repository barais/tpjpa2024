package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("U")
public class Utilisateur extends Personne {

    private List<Rdv> rdvs = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur (String nom) {
        super(nom);
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
        return "Utilisateur [id=" + getId() + ", nom=" + getNom() + ", prénom=" + getPrenom() + "]";
    }
}
