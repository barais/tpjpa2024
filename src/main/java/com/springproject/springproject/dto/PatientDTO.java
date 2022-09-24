package com.springproject.springproject.dto;

import com.springproject.springproject.domain.Person;
import lombok.Data;

@Data
public class PatientDTO extends Person {

    private Long id;
    private Long numSS;

}