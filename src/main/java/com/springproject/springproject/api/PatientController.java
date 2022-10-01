package com.springproject.springproject.api;

import com.springproject.springproject.domain.TimeSlot;
import com.springproject.springproject.service.TimeSlotDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import com.springproject.springproject.domain.Patient;
import com.springproject.springproject.dto.PatientDTO;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.service.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
class PatientController {

    private final PatientDAO patientDAO;
    private final TimeSlotDAO timeSlotDAO;

    @Autowired
    private ModelMapper modelMapper;

    PatientController(PatientDAO patientDAO, TimeSlotDAO timeSlotDAO) {
        this.patientDAO = patientDAO;
        this.timeSlotDAO = timeSlotDAO;
    }

    @Operation(summary = "Get all patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the patient",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @GetMapping("")
    @ResponseStatus(code= HttpStatus.OK)
    List<PatientDTO> all() {
        //Get all patients in a list
        List<Patient> listPatients = patientDAO.findAll();

        //convert this list of Patient (entity) to a list of PatientDTO
        return listPatients
                .stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Post a new patient in data base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "patient successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @PostMapping("")
    @ResponseStatus(code= HttpStatus.CREATED)
    PatientDTO newPatient(@RequestBody Patient newPatientDTO) {
        Patient patientEntity = modelMapper.map(newPatientDTO, Patient.class);

        Patient savedPatient = patientDAO.save(patientEntity);

        return modelMapper.map(savedPatient, PatientDTO.class);
    }

    @Operation(summary = "Get patient by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the patient",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @GetMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    PatientDTO one(@PathVariable Long id) throws PatientNotFoundException {
        Patient patient = patientDAO.findById(id).orElseThrow(() -> new PatientNotFoundException(id));

        return modelMapper.map(patient, PatientDTO.class);
    }

    @Operation(summary = "Update a specific patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @PutMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    PatientDTO replacePatient(@RequestBody Patient newPatient, @PathVariable Long id) throws PatientNotFoundException {
        Patient updatedPatient = patientDAO.findById(id)
                .map(Patient -> {
                    Patient.setFirstName(newPatient.getFirstName());
                    Patient.setLastName(newPatient.getLastName());
                    Patient.setNumSS(newPatient.getNumSS());
                    return patientDAO.save(Patient);
                })
                .orElseThrow(() -> new PatientNotFoundException(id));

        return modelMapper.map(updatedPatient, PatientDTO.class);
    }

    @Operation(summary = "Delete a patient by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient successfully deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)) }),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    void deletePatient(@PathVariable Long id) throws PatientNotFoundException {
        Patient patient = patientDAO.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
        Optional<List<TimeSlot>> timeSlot = timeSlotDAO.getTimeSlotByPatient(patient);
        if(timeSlot.isPresent()) {
            for(TimeSlot timeSlotToUpdate : timeSlot.get()) {
                timeSlotToUpdate.setPatient(null);
                timeSlotDAO.save(timeSlotToUpdate);
            }
        }
        patientDAO.delete(patient);
    }
}