package com.springproject.springproject.dto;

import com.springproject.springproject.domain.Person;
import com.springproject.springproject.domain.Specialisation;
import lombok.Data;

import java.util.List;

@Data
public class DoctorDTO extends Person {

    private Long id;
    private Specialisation specialisation;
    private List<String> freeTimeSlots;

}
