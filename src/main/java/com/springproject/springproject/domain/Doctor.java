package com.springproject.springproject.domain;

import com.sun.istack.NotNull;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Doctor extends Person {

    private Specialisation specialisation;


    private final static int DURATION_TIME_SLOT = 30;

    public Doctor() {
        Date oldDate = new Date();
        Date newDate = DateUtils.addHours(oldDate, 3);
        //this.timeSlots.addAll(setFreeTimeSlots(oldDate, newDate));
    }

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

//    @ElementCollection
//    public List<TimeSlot> getFreeTimeSlots() {
//        return timeSlots;
//    }
//
//    public void setFreeTimeSlots(List<TimeSlot> timeSlot) {
//        this.timeSlots = timeSlot;
//    }

    @Override
    public String toString() {
        return "Medecin [id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", specialisation=" + specialisation.toString();
    }

//    /**
//     * This method will setup the list of free time for each doctors created
//     * @return the list of free time slots
//     */
//    public List<TimeSlot> setFreeTimeSlots(Date begin, Date end) {
//        List<TimeSlot> ret = new ArrayList<>();
//
//        LocalDateTime startDate = LocalDateTime.ofInstant(begin.toInstant(), ZoneId.systemDefault());
//        LocalDateTime endDate = LocalDateTime.ofInstant(begin.toInstant(), ZoneId.systemDefault());
//
//        int nbSlots = 0;
//
//        LocalDateTime currentSlot = startDate;
//
//        while(currentSlot.isBefore(endDate)) {
//            currentSlot = startDate.plusMinutes(DURATION_TIME_SLOT * nbSlots);
//
//            // Convert to Date object
//            ZonedDateTime zdt = currentSlot.atZone(ZoneId.systemDefault());
//            Date timeSlotDate = Date.from(zdt.toInstant());
//            // Add in a new TimeSlot Object
//            TimeSlot newSlot = new TimeSlot(this, null, timeSlotDate);
//
//            ret.add(newSlot);
//        }
//
//        return ret;
//    }


}

