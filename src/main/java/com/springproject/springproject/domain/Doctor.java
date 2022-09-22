package com.springproject.springproject.domain;

import com.sun.istack.NotNull;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Doctor extends Person {

    private Specialisation specialisation;

    private List<String> freeTimeSlots;

    private static String START_OF_DAY = "09:00";
    private static String END_OF_DAY = "18:00";

    public Doctor() { this.freeTimeSlots = setFreeTimeSlots(); }

    public Doctor(String firstName, String lastName, Specialisation spe) {
        super(firstName, lastName);
        this.specialisation = spe;
    }

    public Doctor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    @ElementCollection
    public List<String> getFreeTimeSlots() {
        return freeTimeSlots;
    }

    public void setFreeTimeSlots(List<String> freeTimeSlots) {
        this.freeTimeSlots = freeTimeSlots;
    }

    @Override
    public String toString() {
        return "Medecin [id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", specialisation=" + specialisation.toString();
    }

    /**
     * This method will setup the list of free time for each doctors created
     * @return the list of free time slots
     */
    public List<String> setFreeTimeSlots() {
        List<String> rep = new ArrayList<>();
        LocalTime timeSlot = LocalTime.parse(START_OF_DAY);
        rep.add(timeSlot.toString());
        while(timeSlot.isBefore(LocalTime.parse(END_OF_DAY))) {
            LocalTime newTimeSlot = timeSlot.plusMinutes(30);
            rep.add(newTimeSlot.toString());
            timeSlot = newTimeSlot;
        }
        return rep;
    }

    /**
     * This method will be called when an appointment is booked. We therefore remove the free time of the appointment's doctor
     * @param timeSlot the time slot we want to remove
     */
    public void book(String timeSlot) {
        freeTimeSlots.remove(timeSlot);
    }
}

