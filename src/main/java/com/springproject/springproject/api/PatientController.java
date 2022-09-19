package com.springproject.springproject.api;

import com.springproject.springproject.domain.Patient;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
class PatientController {

    private final PatientDAO patientDAO;

    PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @GetMapping("")
    List<Patient> all() {
        return patientDAO.findAll();
    }

    @PostMapping("")
    Patient newPatient(@RequestBody Patient newPatient) {
        return patientDAO.save(newPatient);
    }

    @GetMapping("{id}")
    Patient one(@PathVariable Long id) throws PatientNotFoundException {
        return patientDAO.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @PutMapping("{id}")
    Patient replacePatient(@RequestBody Patient newPatient, @PathVariable Long id) {

        return patientDAO.findById(id)
                .map(Patient -> {
                    Patient.setFirstName(newPatient.getFirstName());
                    Patient.setLastName(newPatient.getLastName());
                    Patient.setNumSS(newPatient.getNumSS());
                    return patientDAO.save(Patient);
                })
                .orElseGet(() -> {
                    newPatient.setId(id);
                    return patientDAO.save(newPatient);
                });
    }

    @DeleteMapping("{id}")
    void deletePatient(@PathVariable Long id) {
        patientDAO.deleteById(id);
    }
}