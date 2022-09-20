package com.springproject.springproject.domain;

import lombok.Data;

@Data
public class DoctorDTO extends Person {

    private Long id;
    private Specialisation spe;

}
