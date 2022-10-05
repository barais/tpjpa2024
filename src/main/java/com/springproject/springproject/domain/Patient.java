package com.springproject.springproject.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Patient extends Person {

    private Long numSS;
    private List<TimeSlot> timeSlot;

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

    @OneToMany(mappedBy = "patient")
    public List<TimeSlot> getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(List<TimeSlot> timeSlot) {
        this.timeSlot = timeSlot;
    }

}
