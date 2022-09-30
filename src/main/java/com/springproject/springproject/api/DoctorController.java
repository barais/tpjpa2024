package com.springproject.springproject.api;

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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/doctors")
@Valid
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DoctorController {

    private final DoctorDAO dao;

    @Autowired
    private ModelMapper modelMapper;

    DoctorController(DoctorDAO dao) {
        this.dao = dao;
    }

    @GetMapping("")
    List<DoctorDTO> all() {
        List<Doctor> listDoctors = dao.findAll();

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

        Doctor savedDoctor = dao.save(doctorEntity);

        //Convert saved entity to a DTO
        return modelMapper.map(savedDoctor, DoctorDTO.class);
    }

    @GetMapping("/{id}")
    DoctorDTO one(@PathVariable Long id) throws DoctorNotFoundException {
        Doctor doctor = dao.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));

        //Convert entity to DTO and return it
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @GetMapping("/specialisation/{specialisation}")
    List<DoctorDTO> getAllDoctorsWithSpeciality(@PathVariable String specialisation) {
        List<Doctor> listDoctors = dao.getAllBySpecialities(Specialisation.valueOf(specialisation));

        return listDoctors
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    DoctorDTO replaceDoctor(@RequestBody DoctorDTO newDoctorDTO, @PathVariable Long id) {
        Doctor updatedDoctor = dao.findById(id)
                .map(doctor -> {
                    doctor.setFirstName(newDoctorDTO.getFirstName());
                    doctor.setLastName(newDoctorDTO.getLastName());
                    doctor.setSpecialisation(newDoctorDTO.getSpecialisation());
                    return dao.save(doctor);
                })
                .orElseGet(() -> {
                    newDoctorDTO.setId(id);
                    return dao.save(modelMapper.map(newDoctorDTO, Doctor.class));
                });

        return modelMapper.map(updatedDoctor, DoctorDTO.class);
    }

    @DeleteMapping("/{id}")
    void deleteDoctor(@PathVariable Long id) {
        dao.deleteById(id);
    }
}
