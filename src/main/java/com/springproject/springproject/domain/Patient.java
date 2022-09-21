package com.springproject.springproject.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;

@Entity
public class Patient extends Person {

    private Long numSS;

    public Patient() {}

    public Patient(String firstName, String lastName, Long numSS) {
        super(firstName, lastName);
        this.numSS = numSS;
    }

    @NotNull
    public Long getNumSS() {
        return numSS;
    }

    public void setNumSS(Long numSS) {
        this.numSS = numSS;
    }

}
