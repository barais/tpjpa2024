package com.springproject.springproject.api;

import org.modelmapper.ModelMapper;
import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.dto.DoctorDTO;
import com.springproject.springproject.domain.Specialisation;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.service.DoctorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/doctors")
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

    @PostMapping("")
    DoctorDTO newDoctor(@RequestBody Doctor doctorDTO) {
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
    DoctorDTO replaceDoctor(@RequestBody Doctor newDoctor, @PathVariable Long id) {
        Doctor updatedDoctor = dao.findById(id)
                .map(doctor -> {
                    doctor.setFirstName(newDoctor.getFirstName());
                    doctor.setLastName(newDoctor.getLastName());
                    doctor.setSpecialisation(newDoctor.getSpecialisation());
                    return dao.save(doctor);
                })
                .orElseGet(() -> {
                    newDoctor.setId(id);
                    return dao.save(newDoctor);
                });

        return modelMapper.map(updatedDoctor, DoctorDTO.class);
    }

    @DeleteMapping("/{id}")
    void deleteDoctor(@PathVariable Long id) {
        dao.deleteById(id);
    }
}
