package com.springproject.springproject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Doctor extends Person implements Serializable {

    @NotNull
    private Specialisation spe;

    public Doctor() {}

    public Doctor(String firstName, String lastName, Specialisation spe) {
        super(firstName, lastName);
        this.spe = spe; }

    public Doctor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Enumerated(EnumType.ORDINAL)
    public Specialisation getSpecialisation() {
        return spe;
    }

    @Enumerated(EnumType.ORDINAL)
    public void setSpecialisation(Specialisation spe) {
        this.spe = spe;
    }

    @Override
    public String toString() {
        return "Medecin [id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", specialisation=" + spe;
    }
}

