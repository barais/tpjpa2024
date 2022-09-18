package com.springproject.springproject.exception;

public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(Long id) {
        super("Patient with id " + id + " not found.");
    }
}
