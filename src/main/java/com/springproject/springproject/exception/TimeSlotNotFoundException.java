package com.springproject.springproject.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "TimeSlot not found")
public class TimeSlotNotFoundException extends Exception {
    public TimeSlotNotFoundException(Long id) {
        super("Appointment with id " + id + " not found.");
    }
    public TimeSlotNotFoundException() {
        super("Appointment not found.");
    }

}
