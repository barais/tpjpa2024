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
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<DoctorDTO>> all() {
        List<Doctor> listDoctors = doctorDAO.findAll();
        List<DoctorDTO> listDoctorsDTO = listDoctors
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).header("GET ALL DOCTORS IN DATABASE").body(listDoctorsDTO);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    @ResponseStatus(code= HttpStatus.CREATED)
    ResponseEntity<DoctorDTO> newDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        //Convert DTO to entity
        Doctor doctorEntity = modelMapper.map(doctorDTO, Doctor.class);

        Doctor savedDoctor = doctorDAO.save(doctorEntity);

        //Convert saved entity to a DTO
        return ResponseEntity.ok()
                .body(modelMapper.map(savedDoctor, DoctorDTO.class));
    }

    @GetMapping("/{id}")
    ResponseEntity<DoctorDTO> one(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorDAO.findById(id);

        if(doctor.isEmpty()) return ResponseEntity.notFound().build();

        //Convert entity to DTO and return it
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(doctor.get(), DoctorDTO.class));
    }

    @GetMapping("/specialisation/{specialisation}")
    ResponseEntity<List<DoctorDTO>> getAllDoctorsWithSpeciality(@PathVariable String specialisation) {
        List<Doctor> listDoctors = doctorDAO.getAllBySpecialities(Specialisation.valueOf(specialisation));

        return ResponseEntity.status(HttpStatus.OK)
                .body(listDoctors
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    ResponseEntity<DoctorDTO> replaceDoctor(@RequestBody DoctorDTO newDoctorDTO, @PathVariable Long id) {
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

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedDoctor, DoctorDTO.class));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorDAO.findById(id);

        if(doctor.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor "+id+" not found");

        Optional<List<TimeSlot>> timeSlot = timeSlotDAO.getTimeSlotByDoctor(doctor.get());
        timeSlot.ifPresent(timeSlotDAO::deleteAll);
        doctorDAO.delete(doctor.get());

        return ResponseEntity.status(HttpStatus.OK).body("Doctor "+id+" deleted.");
    }
}
