package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue; import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employe {

    private Long id;
    private String name;
    private Specialisation spe;

    public Employe() {}

    public Employe(String name, Specialisation spe) {
        this.name = name;
        this.spe = spe; }

    public Employe(String name) {
        this.name = name;
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

    @ManyToOne
    public Specialisation getSpecialisation() {
        return spe;
    }

    public void setSpecialisation(Specialisation spe) {
        this.spe = spe;
    }

    @Override
    public String toString() {
        return "Medecin [id=" + id + ", name=" + name + ", specialisation=" + spe;
    }
}

