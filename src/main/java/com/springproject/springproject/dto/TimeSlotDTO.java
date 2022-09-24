package com.springproject.springproject.dto;

import lombok.Data;

@Data
public class TimeSlotDTO {

    private Long id;

    private Long doctor;
    private Long patient;
    private String date;

    public TimeSlotDTO(){};
    public TimeSlotDTO(Long doctor, Long patient, String date){
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    };

}
