package com.springproject.springproject.api;

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
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * This method will get all the time slots in data base
     * @return list of time slot DTO
     */
    @GetMapping("")
    List<TimeSlotDTO> all() {
        List<TimeSlot> listTimeSlots = timeSlotDAO.findAll();

        return listTimeSlots
                .stream()
                .map(appointment -> timeSlotMapper.toDTO(appointment))
                .collect(Collectors.toList());
    }

    /**
     * this method will create a new time slot
     * @param timeSlotDTO contains a date, a doctor and a patient
     * @return TimeSlotDTO the new time slot created
     * @throws DoctorNotFoundException
     * @throws PatientNotFoundException
     * @throws ParseException
     * @throws TimeSlotNotFoundException
     */
    @PostMapping("")
    TimeSlotDTO newTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO) throws DoctorNotFoundException, PatientNotFoundException, ParseException, TimeSlotNotFoundException {
        TimeSlot newTimeSlot = timeSlotMapper.toEntity(timeSlotDTO);

        // We look if we find an appointment that match the date and the doctor provided, we "update" this time slot by adding the patient
        // or else we throw an error telling us that this time slot could not be found
        TimeSlot updatedTimeSlot = timeSlotDAO.getTimeSlotByDate(newTimeSlot.getDate(), newTimeSlot.getDoctor())
                .map(TimeSlot -> {
                    TimeSlot.setPatient(newTimeSlot.getPatient());
                    TimeSlot.setDoctor(newTimeSlot.getDoctor());
                    TimeSlot.setDate(newTimeSlot.getDate());
                    return timeSlotDAO.save(TimeSlot);
                })
                .orElseThrow(TimeSlotNotFoundException::new);

        return timeSlotMapper.toDTO(updatedTimeSlot);
    }

    /**
     * This method will setup a list of 30min free time slot between 2 date
     * @param timeRangeDTO contains startDate, endDate, and idDoctor
     * @return the list of free time slots
     */
    @PostMapping("/setNewTimeSlots")
    List<TimeSlotDTO> setNewTimeSlots(@RequestBody TimeRangeDTO timeRangeDTO) throws ParseException, DoctorNotFoundException, PatientNotFoundException {
        return SetNewTimeSlots.setNewTimeSlots(timeRangeDTO, timeSlotDAO, timeSlotMapper);
    }

    /**
     * get only one time slot by its ID
     * @param id of the time slot
     * @return a time slot
     * @throws TimeSlotNotFoundException
     */
    @GetMapping("/{id}")
    TimeSlotDTO one(@PathVariable Long id) throws TimeSlotNotFoundException {
        TimeSlot timeSlot = timeSlotDAO.findById(id).orElseThrow(() -> new TimeSlotNotFoundException(id));
        return timeSlotMapper.toDTO(timeSlot);
    }

    /**
     *
     * @param newTimeSlot
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    TimeSlotDTO replaceTimeSlot(@RequestBody TimeSlot newTimeSlot, @PathVariable Long id) {
        TimeSlot updatedTimeSlot = timeSlotDAO.findById(id)
                .map(timeSlot -> {
                    timeSlot.setPatient(newTimeSlot.getPatient());
                    timeSlot.setDoctor(newTimeSlot.getDoctor());
                    timeSlot.setDate(newTimeSlot.getDate());
                    return timeSlotDAO.save(timeSlot);
                })
                .orElseGet(() -> {
                    newTimeSlot.setId(id);
                    return timeSlotDAO.save(newTimeSlot);
                });

        return timeSlotMapper.toDTO(updatedTimeSlot);
    }

    @DeleteMapping("/{id}")
    void deleteTimeSlot(@PathVariable Long id) {
        timeSlotDAO.deleteById(id);
    }
}
