package com.springproject.springproject.mapper;

import com.springproject.springproject.domain.Appointment;
import com.springproject.springproject.domain.AppointmentDTO;
import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.domain.Patient;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.service.DoctorDAO;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    @Autowired
    DoctorDAO doctorDAO;
    @Autowired
    PatientDAO patientDAO;

    public AppointmentDTO toDTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setDoctor(appointment.getDoctor().getId());
        appointmentDTO.setPatient(appointment.getPatient().getId());
        appointmentDTO.setTimeSlot(appointment.getTimeSlot());
        appointment.getDoctor().book(appointment.getTimeSlot());

        return appointmentDTO;
    }

    public Appointment toEntity(AppointmentDTO appointmentDTO) throws PatientNotFoundException, DoctorNotFoundException {
        Appointment appointment = new Appointment();

        //We find the doctor in DB (return error if not found)
        Doctor doctor = doctorDAO.findById(appointmentDTO.getDoctor()).orElseThrow(() -> new DoctorNotFoundException(appointmentDTO.getDoctor()));
        appointment.setDoctor(doctor);

        //We find the patient in DB (return error if not found)
        Patient patient = patientDAO.findById(appointmentDTO.getPatient()).orElseThrow(() -> new PatientNotFoundException(appointmentDTO.getPatient()));
        appointment.setPatient(patient);

        appointment.setTimeSlot(appointmentDTO.getTimeSlot());
        appointment.getDoctor().book(appointmentDTO.getTimeSlot());

        return appointment;
    }

}
