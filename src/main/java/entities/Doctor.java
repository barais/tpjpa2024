package entities;

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
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

