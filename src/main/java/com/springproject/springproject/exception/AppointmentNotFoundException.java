package com.springproject.springproject.exception;

public class AppointmentNotFoundException extends Exception {
    public AppointmentNotFoundException(Long id) {
        super("Appointment with id " + id + " not found.");
    }
}
