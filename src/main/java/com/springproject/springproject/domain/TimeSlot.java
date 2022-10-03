package com.springproject.springproject.domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class TimeSlot {

    private Long id;

    private Doctor doctor;
    private Patient patient;
    private Date date;

    public TimeSlot(Doctor doctor, Patient patient, Date date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    public TimeSlot() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @Nullable
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
