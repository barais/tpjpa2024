package com.springproject.springproject.api;

import com.springproject.springproject.domain.Appointment;
import com.springproject.springproject.domain.AppointmentDTO;
import com.springproject.springproject.exception.AppointmentNotFoundException;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.service.AppointmentDAO;
import com.springproject.springproject.service.DoctorDAO;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final ModelMapper modelMapper;
    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;

    AppointmentController(AppointmentDAO dao, DoctorDAO doctorDAO, PatientDAO patientDAO) {
        this.appointmentDAO = dao;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @GetMapping("")
    List<Appointment> all() {
        return appointmentDAO.findAll();
    }

    @PostMapping("")
    Appointment newAppointment(@RequestBody AppointmentDTO appointmentDTO, @PathVariable Long doctorId, @PathVariable Long patientId) throws DoctorNotFoundException, PatientNotFoundException {
        Appointment modelMapper = modelMapper.map()
        Appointment newAppointment = new Appointment(
                doctorDAO.findById(doctorId).orElseThrow(() -> new DoctorNotFoundException(doctorId)),
                patientDAO.findById(patientId).orElseThrow(() -> new PatientNotFoundException(patientId)),
                date);
        return appointmentDAO.save(newAppointment);
    }

    @GetMapping("{id}")
    Appointment one(@PathVariable Long id) throws AppointmentNotFoundException {
        return appointmentDAO.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @PutMapping("{id}")
    Appointment replacePatient(@RequestBody Appointment newAppointment, @PathVariable Long id) {

        return appointmentDAO.findById(id)
                .map(Appointment -> {
                    Appointment.setPatient(newAppointment.getPatient());
                    Appointment.setDoctor(newAppointment.getDoctor());
                    Appointment.setDate(newAppointment.getDate());
                    return appointmentDAO.save(Appointment);
                })
                .orElseGet(() -> {
                    newAppointment.setId(id);
                    return appointmentDAO.save(newAppointment);
                });
    }

    @DeleteMapping("{id}")
    void deleteAppointment(@PathVariable Long id) {
        appointmentDAO.deleteById(id);
    }
}
