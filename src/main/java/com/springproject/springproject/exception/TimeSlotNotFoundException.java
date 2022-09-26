package com.springproject.springproject.exception;

public class TimeSlotNotFoundException extends Exception {
    public TimeSlotNotFoundException(Long id) {
        super("Appointment with id " + id + " not found.");
    }
    public TimeSlotNotFoundException() {
        super("Appointment not found.");
    }

}
