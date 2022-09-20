package com.springproject.springproject.api;

import org.modelmapper.ModelMapper;
import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.domain.DoctorDTO;
import com.springproject.springproject.domain.Specialisation;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.service.DoctorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    List<Doctor> all() {
        return dao.findAll();
    }

    @PostMapping("/{specialisation}")
    Doctor newDoctor(@RequestBody Doctor doctor, @PathVariable String specialisation) {
        // TODO : handle enum not found
        doctor.setSpecialisation(Specialisation.valueOf(specialisation));
        return dao.save(doctor);
    }

    @GetMapping("/{id}")
    DoctorDTO one(@PathVariable Long id) throws DoctorNotFoundException {
        Doctor doctor = dao.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));

        //Convert entity to DTO
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);

        return doctorDTO;
    }

    @GetMapping("/specialisation/{specialisation}")
    List<Doctor> getAllDoctorsWithSpeciality(@PathVariable String specialisation) throws DoctorNotFoundException {
        return dao.getAllBySpecialities(Specialisation.valueOf(specialisation));
    }

    @PutMapping("/{id}")
    Doctor replaceDoctor(@RequestBody Doctor newDoctor, @PathVariable Long id) {
        return dao.findById(id)
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
    }

    @DeleteMapping("/{id}")
    void deleteDoctor(@PathVariable Long id) {
        dao.deleteById(id);
    }
}
