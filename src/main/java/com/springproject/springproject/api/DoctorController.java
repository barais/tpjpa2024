package com.springproject.springproject.api;

import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.domain.Specialisation;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.service.DoctorDAO;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorDAO dao;

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
    Doctor one(@PathVariable Long id) throws DoctorNotFoundException {
        return dao.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @GetMapping("/{specialisation}")
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
