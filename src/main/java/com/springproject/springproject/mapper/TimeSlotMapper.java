package com.springproject.springproject.mapper;

import com.springproject.springproject.domain.TimeSlot;
import com.springproject.springproject.dto.TimeSlotDTO;
import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.domain.Patient;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.service.DoctorDAO;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper {

    @Autowired
    DoctorDAO doctorDAO;
    @Autowired
    PatientDAO patientDAO;

    public TimeSlotDTO toDTO(TimeSlot appointment) {
        TimeSlotDTO TImeSlotDTO = new TimeSlotDTO();

        TImeSlotDTO.setDoctor(appointment.getDoctor().getId());
        TImeSlotDTO.setPatient(appointment.getPatient().getId());
        TImeSlotDTO.setTimeSlot(appointment.getTimeSlot());
        appointment.getDoctor().book(appointment.getTimeSlot());

        return TImeSlotDTO;
    }

    public TimeSlot toEntity(TimeSlotDTO TImeSlotDTO) throws PatientNotFoundException, DoctorNotFoundException {
        TimeSlot appointment = new TimeSlot();

        //We find the doctor in DB (return error if not found)
        Doctor doctor = doctorDAO.findById(TImeSlotDTO.getDoctor()).orElseThrow(() -> new DoctorNotFoundException(TImeSlotDTO.getDoctor()));
        appointment.setDoctor(doctor);

        //We find the patient in DB (return error if not found)
        Patient patient = patientDAO.findById(TImeSlotDTO.getPatient()).orElseThrow(() -> new PatientNotFoundException(TImeSlotDTO.getPatient()));
        appointment.setPatient(patient);

        appointment.setTimeSlot(TImeSlotDTO.getTimeSlot());
        appointment.getDoctor().book(TImeSlotDTO.getTimeSlot());

        return appointment;
    }

}
