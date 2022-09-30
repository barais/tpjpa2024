package com.springproject.springproject.api;

import com.springproject.springproject.domain.TimeSlot;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.service.TimeSlotDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.dto.DoctorDTO;
import com.springproject.springproject.domain.Specialisation;
import com.springproject.springproject.service.DoctorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/doctors")
@Valid
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DoctorController {

    private final DoctorDAO doctorDAO;
    private final TimeSlotDAO timeSlotDAO;

    @Autowired
    private ModelMapper modelMapper;

    DoctorController(DoctorDAO doctorDAO, TimeSlotDAO timeSlotDAO) {
        this.doctorDAO = doctorDAO;
        this.timeSlotDAO = timeSlotDAO;
    }

    @Operation(summary = "Get all doctors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the doctor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class)) }),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @GetMapping("")
    ResponseEntity<List<DoctorDTO>> all() {
        List<Doctor> listDoctors = doctorDAO.findAll();
        List<DoctorDTO> listDoctorsDTO = listDoctors
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).header("GET ALL DOCTORS IN DATABASE").body(listDoctorsDTO);
    }

    @Operation(summary = "Post a new doctor in data base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "doctor successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class)) }),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    @ResponseStatus(code= HttpStatus.CREATED)
    ResponseEntity<DoctorDTO> newDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        //Convert DTO to entity
        Doctor doctorEntity = modelMapper.map(doctorDTO, Doctor.class);

        Doctor savedDoctor = doctorDAO.save(doctorEntity);

        //Convert saved entity to a DTO
        return ResponseEntity.ok()
                .body(modelMapper.map(savedDoctor, DoctorDTO.class));
    }


    @Operation(summary = "Get doctor by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the doctor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class)) }),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
             })
    @GetMapping("/{id}")
    ResponseEntity<DoctorDTO> one(@Parameter(description = "id of doctor to be searched") @PathVariable Long id) {
        Optional<Doctor> doctor = doctorDAO.findById(id);

        if(doctor.isEmpty()) return ResponseEntity.notFound().build();

        //Convert entity to DTO and return it
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(doctor.get(), DoctorDTO.class));
    }

    @Operation(summary = "Get all doctors by a specific speciality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found doctors",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class)) }),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @GetMapping("/specialisation/{specialisation}")
    ResponseEntity<List<DoctorDTO>> getAllDoctorsWithSpeciality(@PathVariable String specialisation) {
        List<Doctor> listDoctors = doctorDAO.getAllBySpecialities(Specialisation.valueOf(specialisation));

        return ResponseEntity.status(HttpStatus.OK)
                .body(listDoctors
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Update a specific doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class)) }),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @PutMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    ResponseEntity<DoctorDTO> replaceDoctor(@RequestBody DoctorDTO newDoctorDTO, @Parameter(description = "id of doctor to be searched") @PathVariable Long id) throws DoctorNotFoundException {
        Doctor updatedDoctor = doctorDAO.findById(id)
                .map(doctor -> {
                    doctor.setFirstName(newDoctorDTO.getFirstName());
                    doctor.setLastName(newDoctorDTO.getLastName());
                    doctor.setSpecialisation(newDoctorDTO.getSpecialisation());
                    return doctorDAO.save(doctor);
                })
                .orElseThrow(() -> new DoctorNotFoundException(id));

        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(updatedDoctor, DoctorDTO.class));
    }

    @Operation(summary = "Delete a doctor by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor successfully deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class)) }),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteDoctor(@Parameter(description = "id of doctor to be searched") @PathVariable Long id) throws DoctorNotFoundException {
        Doctor doctor = doctorDAO.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));

        Optional<List<TimeSlot>> timeSlot = timeSlotDAO.getTimeSlotByDoctor(doctor);
        timeSlot.ifPresent(timeSlotDAO::deleteAll);
        doctorDAO.delete(doctor);

        return ResponseEntity.status(HttpStatus.OK).body("Doctor "+id+" deleted.");
    }
}
