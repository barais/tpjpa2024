package com.springproject.springproject.api;

import com.springproject.springproject.domain.TimeSlot;
import com.springproject.springproject.service.TimeSlotDAO;
import org.modelmapper.ModelMapper;
import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.dto.DoctorDTO;
import com.springproject.springproject.domain.Specialisation;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.service.DoctorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/doctors")
@Valid
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DoctorController {

    private final DoctorDAO doctorDAO;
    private final TimeSlotDAO timeSlotDAO;

    @Autowired
    private ModelMapper modelMapper;

    DoctorController(DoctorDAO doctorDAO, TimeSlotDAO timeSlotDAO) {
        this.doctorDAO = doctorDAO;
        this.timeSlotDAO = timeSlotDAO;
    }

    @GetMapping("")
    List<DoctorDTO> all() {
        List<Doctor> listDoctors = doctorDAO.findAll();

        return listDoctors
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    @ResponseStatus(code= HttpStatus.CREATED)
    DoctorDTO newDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        //Convert DTO to entity
        Doctor doctorEntity = modelMapper.map(doctorDTO, Doctor.class);

        Doctor savedDoctor = doctorDAO.save(doctorEntity);

        //Convert saved entity to a DTO
        return modelMapper.map(savedDoctor, DoctorDTO.class);
    }

    @GetMapping("/{id}")
    DoctorDTO one(@PathVariable Long id) throws DoctorNotFoundException {
        Doctor doctor = doctorDAO.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));

        //Convert entity to DTO and return it
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @GetMapping("/specialisation/{specialisation}")
    List<DoctorDTO> getAllDoctorsWithSpeciality(@PathVariable String specialisation) {
        List<Doctor> listDoctors = doctorDAO.getAllBySpecialities(Specialisation.valueOf(specialisation));

        return listDoctors
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    DoctorDTO replaceDoctor(@RequestBody DoctorDTO newDoctorDTO, @PathVariable Long id) {
        Doctor updatedDoctor = doctorDAO.findById(id)
                .map(doctor -> {
                    doctor.setFirstName(newDoctorDTO.getFirstName());
                    doctor.setLastName(newDoctorDTO.getLastName());
                    doctor.setSpecialisation(newDoctorDTO.getSpecialisation());
                    return doctorDAO.save(doctor);
                })
                .orElseGet(() -> {
                    newDoctorDTO.setId(id);
                    return doctorDAO.save(modelMapper.map(newDoctorDTO, Doctor.class));
                });

        return modelMapper.map(updatedDoctor, DoctorDTO.class);
    }

    @DeleteMapping("/{id}")
    void deleteDoctor(@PathVariable Long id) throws DoctorNotFoundException {
        Doctor doctor = doctorDAO.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
        Optional<List<TimeSlot>> timeSlot = timeSlotDAO.getTimeSlotByDoctor(doctor);
        timeSlot.ifPresent(timeSlotDAO::deleteAll);
        doctorDAO.delete(doctor);
    }
}
