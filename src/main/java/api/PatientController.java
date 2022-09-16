package api;

import domain.Patient;
import exception.PatientNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.PatientDAO;

import java.util.List;

@RestController
class PatientController {

    private final PatientDAO patientDAO;

    PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/patients")
    List<Patient> all() {
        return patientDAO.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/patients")
    Patient newPatient(@RequestBody Patient newPatient) {
        return patientDAO.save(newPatient);
    }

    // Single item

    @GetMapping("/patients/{id}")
    Patient one(@PathVariable Long id) throws PatientNotFoundException {
        return patientDAO.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @PutMapping("/patients/{id}")
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

    @DeleteMapping("/patients/{id}")
    void deletePatient(@PathVariable Long id) {
        patientDAO.deleteById(id);
    }
}