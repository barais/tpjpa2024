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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class TimeSlotMapper {

    @Autowired
    DoctorDAO doctorDAO;
    @Autowired
    PatientDAO patientDAO;

    public static final SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss" );


    public TimeSlotDTO toDTO(TimeSlot timeSlot) {
        TimeSlotDTO timeSlotDTO = new TimeSlotDTO();

        timeSlotDTO.setDoctor(timeSlot.getDoctor().getId());
        if (!Objects.isNull(timeSlot.getPatient())) { timeSlotDTO.setPatient(timeSlot.getPatient().getId()); }
        timeSlotDTO.setDate(sd.format(timeSlot.getDate()));
        timeSlotDTO.setId(timeSlot.getId());

        return timeSlotDTO;
    }

    public TimeSlot toEntity(TimeSlotDTO timeSlotDTO) throws PatientNotFoundException, DoctorNotFoundException, ParseException {
        TimeSlot timeSlot = new TimeSlot();

        timeSlot.setId(timeSlotDTO.getId());


        //We find the doctor in DB (return error if not found)
        Doctor doctor = doctorDAO.findById(timeSlotDTO.getDoctor()).orElseThrow(() -> new DoctorNotFoundException(timeSlotDTO.getDoctor()));
        timeSlot.setDoctor(doctor);

        //We find the patient in DB (return error if not found)
        Patient patient = patientDAO.findById(timeSlotDTO.getPatient()).orElseThrow(() -> new PatientNotFoundException(timeSlotDTO.getPatient()));
        timeSlot.setPatient(patient);

        timeSlot.setDate(sd.parse(timeSlotDTO.getDate()));

        return timeSlot;
    }

}
