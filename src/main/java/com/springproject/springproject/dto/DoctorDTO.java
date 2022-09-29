package com.springproject.springproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springproject.springproject.domain.Person;
import com.springproject.springproject.domain.Specialisation;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DoctorDTO extends Person {


    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "firstName may not be null")
    private String firstName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "lastName may not be null")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "specialisation may not be null")
    private Specialisation specialisation;

}
