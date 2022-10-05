package com.springproject.springproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Doctor not found")
public class DoctorNotFoundException extends Exception {

    public DoctorNotFoundException(Long id) {
        super("Doctor with id " + id + " not found.");
    }

}
