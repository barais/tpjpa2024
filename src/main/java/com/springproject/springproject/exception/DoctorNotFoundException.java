package com.springproject.springproject.exception;

public class DoctorNotFoundException extends Exception {
    public DoctorNotFoundException(Long id) {
        super("Doctor with id " + id + " not found.");
    }
}
