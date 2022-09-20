package com.springproject.springproject.domain;

import lombok.Data;

@Data
public class PatientDTO extends Person {

    private Long id;
    private Long numSS;

}