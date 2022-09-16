package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Doctor extends Person implements Serializable {

    private Specialisation spe;

    public Doctor() {}

    public Doctor(String firstName, String lastName, Specialisation spe) {
        super(firstName, lastName);
        this.spe = spe; }

    public Doctor(String firstName, String lastName) {
        super(firstName, lastName);
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
        return "Medecin [id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", specialisation=" + spe;
    }
}

