package com.springproject.springproject.api;

import com.springproject.springproject.domain.TimeSlot;
import com.springproject.springproject.dto.TimeSlotDTO;
import com.springproject.springproject.exception.TimeSlotNotFoundException;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.mapper.TimeSlotMapper;
import com.springproject.springproject.service.TimeSlotDAO;
import com.springproject.springproject.service.DoctorDAO;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/slot")
public class TimeSlotController {

    private final TimeSlotDAO appointmentDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;
    @Autowired
    private TimeSlotMapper appointmentMapper;

    TimeSlotController(TimeSlotDAO dao, DoctorDAO doctorDAO, PatientDAO patientDAO) {
        this.appointmentDAO = dao;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @GetMapping("")
    List<TimeSlotDTO> all() {
        List<TimeSlot> listTimeSlots = appointmentDAO.findAll();

        return listTimeSlots
                .stream()
                .map(appointment -> appointmentMapper.toDTO(appointment))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    TimeSlotDTO newTimeSlot(@RequestBody TimeSlotDTO appointmentDTO) throws DoctorNotFoundException, PatientNotFoundException {
        TimeSlot appointment = appointmentMapper.toEntity(appointmentDTO);
        TimeSlot savedTimeSlot = appointmentDAO.save(appointment);

        return appointmentMapper.toDTO(savedTimeSlot);
    }

    @GetMapping("/{id}")
    TimeSlotDTO one(@PathVariable Long id) throws TimeSlotNotFoundException {
        TimeSlot appointment = appointmentDAO.findById(id).orElseThrow(() -> new TimeSlotNotFoundException(id));
        return appointmentMapper.toDTO(appointment);
    }

    @PutMapping("/{id}")
    TimeSlotDTO replacePatient(@RequestBody TimeSlot newTimeSlot, @PathVariable Long id) {
        TimeSlot updatedTimeSlot = appointmentDAO.findById(id)
                .map(TimeSlot -> {
                    TimeSlot.setPatient(newTimeSlot.getPatient());
                    TimeSlot.setDoctor(newTimeSlot.getDoctor());
                    TimeSlot.setTimeSlot(newTimeSlot.getTimeSlot());
                    return appointmentDAO.save(TimeSlot);
                })
                .orElseGet(() -> {
                    newTimeSlot.setId(id);
                    return appointmentDAO.save(newTimeSlot);
                });

        return appointmentMapper.toDTO(updatedTimeSlot);
    }

    @DeleteMapping("/{id}")
    void deleteTimeSlot(@PathVariable Long id) {
        appointmentDAO.deleteById(id);
    }
}
