package com.springproject.springproject.api;

import org.modelmapper.ModelMapper;
import com.springproject.springproject.domain.Patient;
import com.springproject.springproject.domain.PatientDTO;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
class PatientController {

    private final PatientDAO patientDAO;

    @Autowired
    private ModelMapper modelMapper;

    PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @GetMapping("")
    List<PatientDTO> all() {
        //Get all patients in a list
        List<Patient> listPatients = patientDAO.findAll();

        //convert this list of Patient (entity) to a list of PatientDTO
        return listPatients
                .stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    PatientDTO newPatient(@RequestBody Patient newPatientDTO) {
        Patient patientEntity = modelMapper.map(newPatientDTO, Patient.class);

        Patient savedPatient = patientDAO.save(patientEntity);

        return modelMapper.map(savedPatient, PatientDTO.class);
    }

    @GetMapping("/{id}")
    PatientDTO one(@PathVariable Long id) throws PatientNotFoundException {
        Patient patient = patientDAO.findById(id).orElseThrow(() -> new PatientNotFoundException(id));

        return modelMapper.map(patient, PatientDTO.class);
    }

    @PutMapping("/{id}")
    PatientDTO replacePatient(@RequestBody Patient newPatient, @PathVariable Long id) {
        Patient updatedPatient = patientDAO.findById(id)
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

        return modelMapper.map(updatedPatient, PatientDTO.class);
    }

    @DeleteMapping("/{id}")
    void deletePatient(@PathVariable Long id) {
        patientDAO.deleteById(id);
    }
}