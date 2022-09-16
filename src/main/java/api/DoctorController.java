package api;

import domain.Doctor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        return dao.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Doctor replaceDoctor(@RequestBody Doctor newDoctor, @PathVariable Long id) {

        return dao.findById(id)
                .map(employee -> {
                    employee.setName(newDoctor.getName());
                    employee.setRole(newDoctor.getRole());
                    return dao.save(employee);
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
