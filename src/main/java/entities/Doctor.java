package entities;

import javax.persistence.*;

@Entity
public class Doctor {

    private Long id;
    private String name;
    private Specialisation spe;

    public Doctor() {}

    public Doctor(String name, Specialisation spe) {
        this.name = name;
        this.spe = spe; }

    public Doctor(String name) {
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

    @Transient
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

