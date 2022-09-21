package com.springproject.springproject.api;

import com.springproject.springproject.domain.Appointment;
import com.springproject.springproject.domain.AppointmentDTO;
import com.springproject.springproject.exception.AppointmentNotFoundException;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.mapper.AppointmentMapper;
import com.springproject.springproject.service.AppointmentDAO;
import com.springproject.springproject.service.DoctorDAO;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;
    @Autowired
    private AppointmentMapper appointmentMapper;

    AppointmentController(AppointmentDAO dao, DoctorDAO doctorDAO, PatientDAO patientDAO) {
        this.appointmentDAO = dao;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @GetMapping("")
    List<AppointmentDTO> all() {
        List<Appointment> listAppointments = appointmentDAO.findAll();

        return listAppointments
                .stream()
                .map(appointment -> appointmentMapper.toDTO(appointment))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    AppointmentDTO newAppointment(@RequestBody AppointmentDTO appointmentDTO) throws DoctorNotFoundException, PatientNotFoundException {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        Appointment savedAppointment = appointmentDAO.save(appointment);

        return appointmentMapper.toDTO(savedAppointment);
    }

    @GetMapping("/{id}")
    AppointmentDTO one(@PathVariable Long id) throws AppointmentNotFoundException {
        Appointment appointment = appointmentDAO.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
        return appointmentMapper.toDTO(appointment);
    }

    @PutMapping("/{id}")
    AppointmentDTO replacePatient(@RequestBody Appointment newAppointment, @PathVariable Long id) {
        Appointment updatedAppointment = appointmentDAO.findById(id)
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

        return appointmentMapper.toDTO(updatedAppointment);
    }

    @DeleteMapping("{id}")
    void deleteAppointment(@PathVariable Long id) {
        appointmentDAO.deleteById(id);
    }
}
