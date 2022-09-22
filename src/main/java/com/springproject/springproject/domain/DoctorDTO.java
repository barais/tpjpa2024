package com.springproject.springproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class DoctorDTO extends Person {

    private Long id;
    private Specialisation specialisation;
    private List<String> freeTimeSlots;

}
