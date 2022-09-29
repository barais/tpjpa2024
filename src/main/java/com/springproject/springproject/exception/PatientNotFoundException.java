package com.springproject.springproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Patient not found")
public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(Long id) {
        super("Patient with id " + id + " not found.");
    }
}
