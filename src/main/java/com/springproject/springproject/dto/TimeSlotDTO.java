package com.springproject.springproject.dto;

import lombok.Data;

@Data
public class TimeSlotDTO {

    private Long doctor;
    private Long patient;
    private String timeSlot;

    public TimeSlotDTO(){};

}
