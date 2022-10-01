package com.springproject.springproject.api;

import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.domain.TimeSlot;
import com.springproject.springproject.dto.TimeRangeDTO;
import com.springproject.springproject.dto.TimeSlotDTO;
import com.springproject.springproject.exception.TimeSlotNotFoundException;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.mapper.TimeSlotMapper;
import com.springproject.springproject.service.TimeSlotDAO;
import com.springproject.springproject.service.DoctorDAO;
import com.springproject.springproject.service.PatientDAO;
import com.springproject.springproject.utils.SetNewTimeSlots;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/slot")
public class TimeSlotController {

    private final TimeSlotDAO timeSlotDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;

    @Autowired
    private TimeSlotMapper timeSlotMapper;

    TimeSlotController(TimeSlotDAO dao, DoctorDAO doctorDAO, PatientDAO patientDAO) {
        this.timeSlotDAO = dao;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @Operation(summary = "Get all time slots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found time slots",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class)) }),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @GetMapping("")
    @ResponseStatus(code= HttpStatus.OK)
    List<TimeSlotDTO> all() {
        List<TimeSlot> listTimeSlots = timeSlotDAO.findAll();

        return listTimeSlots
                .stream()
                .map(appointment -> timeSlotMapper.toDTO(appointment))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Create a new time slot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Time slot successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class)) }),
            @ApiResponse(responseCode = "404", description = "Doctor or Patient not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @PostMapping("")
    @ResponseStatus(code= HttpStatus.CREATED)
    TimeSlotDTO newTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO) throws DoctorNotFoundException, PatientNotFoundException, ParseException, TimeSlotNotFoundException {
        TimeSlot newTimeSlot = timeSlotMapper.toEntity(timeSlotDTO);
        timeSlotDAO.save(newTimeSlot);

        return timeSlotMapper.toDTO(newTimeSlot);
    }

    @Operation(summary = "Create new time slots for a specific doctor, put the patient to null")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New time slots successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class)) }),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @PostMapping("/setNewTimeSlots")
    @ResponseStatus(code= HttpStatus.OK)
    List<TimeSlotDTO> setNewTimeSlots(@RequestBody TimeRangeDTO timeRangeDTO) throws ParseException, DoctorNotFoundException, PatientNotFoundException {
        return SetNewTimeSlots.setNewTimeSlots(timeRangeDTO, timeSlotDAO, timeSlotMapper);
    }

    @Operation(summary = "Get a time slot by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time slot found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class)) }),
            @ApiResponse(responseCode = "404", description = "Time slot not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @GetMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    TimeSlotDTO one(@PathVariable Long id) throws TimeSlotNotFoundException {
        TimeSlot timeSlot = timeSlotDAO.findById(id).orElseThrow(() -> new TimeSlotNotFoundException(id));
        return timeSlotMapper.toDTO(timeSlot);
    }

    @Operation(summary = "Update time slot by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time slots successfully updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class)) }),
            @ApiResponse(responseCode = "404", description = "Time slot not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @PutMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    TimeSlotDTO replaceTimeSlot(@RequestBody TimeSlotDTO newTimeSlotDTO, @PathVariable Long id) throws TimeSlotNotFoundException, DoctorNotFoundException, PatientNotFoundException, ParseException {
        newTimeSlotDTO.setId(id);
        TimeSlot newTimeSlot = timeSlotMapper.toEntity(newTimeSlotDTO);
        TimeSlot updatedTimeSlot = timeSlotDAO.findById(id)
                .map(timeSlot -> timeSlotDAO.save(newTimeSlot))
                .orElseThrow(() -> new TimeSlotNotFoundException(id));

        return timeSlotMapper.toDTO(updatedTimeSlot);
    }

    @Operation(summary = "Delete a time slot by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time slot successfully deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class)) }),
            @ApiResponse(responseCode = "404", description = "Time slot not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error server",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    void deleteTimeSlot(@PathVariable Long id) throws TimeSlotNotFoundException {
        TimeSlot timeSlot = timeSlotDAO.findById(id).orElseThrow(() -> new TimeSlotNotFoundException(id));
        timeSlotDAO.delete(timeSlot);
    }
}
