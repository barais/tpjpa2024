package com.springproject.springproject.api;

import com.springproject.springproject.domain.Appointment;
import com.springproject.springproject.exception.AppointmentNotFoundException;
import com.springproject.springproject.service.AppointmentDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentDAO dao;

    AppointmentController(AppointmentDAO dao) {
        this.dao = dao;
    }

    @GetMapping("")
    List<Appointment> all() {
        return dao.findAll();
    }

    @PostMapping("")
    Appointment newAppointment(@RequestBody Appointment newAppointment) {
        return dao.save(newAppointment);
    }

    @GetMapping("{id}")
    Appointment one(@PathVariable Long id) throws AppointmentNotFoundException {
        return dao.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @PutMapping("{id}")
    Appointment replacePatient(@RequestBody Appointment newAppointment, @PathVariable Long id) {

        return dao.findById(id)
                .map(Appointment -> {
                    Appointment.setPatient(newAppointment.getPatient());
                    Appointment.setDoctor(newAppointment.getDoctor());
                    Appointment.setDate(newAppointment.getDate());
                    return dao.save(Appointment);
                })
                .orElseGet(() -> {
                    newAppointment.setId(id);
                    return dao.save(newAppointment);
                });
    }

    @DeleteMapping("{id}")
    void deleteAppointment(@PathVariable Long id) {
        dao.deleteById(id);
    }
}
