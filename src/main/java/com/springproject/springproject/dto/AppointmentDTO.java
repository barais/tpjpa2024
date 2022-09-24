package com.springproject.springproject.dto;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
public class AppointmentDTO {

    private Long doctor;
    private Long patient;
    private String timeSlot;

    public AppointmentDTO(){};

}
