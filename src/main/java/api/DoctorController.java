package api;

import domain.Doctor;
import exception.DoctorNotFoundException;
import org.springframework.web.bind.annotation.*;
import service.DoctorDAO;

import java.util.List;

public class DoctorController {

    private final DoctorDAO dao;

    DoctorController(DoctorDAO dao) {
        this.dao = dao;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Doctor> all() {
        return dao.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/employees")
    Doctor newDoctor(@RequestBody Doctor newDoctor) {
        return dao.save(newDoctor);
    }

    // Single item

    @GetMapping("/employees/{id}")
    Doctor one(@PathVariable Long id) {

        return dao.findById(id).orElseThrow();
    }

    @PutMapping("/employees/{id}")
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

    @DeleteMapping("/employees/{id}")
    void deleteDoctor(@PathVariable Long id) {
        dao.deleteById(id);
    }
}
