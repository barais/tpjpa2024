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

    @Override
    public String toString() {
        return "Medecin [id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", specialisation=" + specialisation.toString();
    }
}

