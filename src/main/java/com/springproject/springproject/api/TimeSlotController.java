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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/slot")
public class TimeSlotController {

    private final TimeSlotDAO timeSlotDAO;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;

    private final static int DURATION_TIME_SLOT = 30;

    @Autowired
    private TimeSlotMapper timeSlotMapper;

    TimeSlotController(TimeSlotDAO dao, DoctorDAO doctorDAO, PatientDAO patientDAO) {
        this.timeSlotDAO = dao;
        this.doctorDAO = doctorDAO;
        this.patientDAO = patientDAO;
    }

    @GetMapping("")
    List<TimeSlotDTO> all() {
        List<TimeSlot> listTimeSlots = timeSlotDAO.findAll();

        return listTimeSlots
                .stream()
                .map(appointment -> timeSlotMapper.toDTO(appointment))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    TimeSlotDTO newTimeSlot(@RequestBody TimeSlotDTO appointmentDTO) throws DoctorNotFoundException, PatientNotFoundException, ParseException {
        TimeSlot appointment = timeSlotMapper.toEntity(appointmentDTO);
        TimeSlot savedTimeSlot = timeSlotDAO.save(appointment);

        return timeSlotMapper.toDTO(savedTimeSlot);
    }

    /**
     * This method will setup a list of 30min free time slot between 2 date
     * @param timeRange contains startDate, endDate, and idDoctor
     * @return the list of free time slots
     */
    @PostMapping("/setfreetimeslot")
    List<TimeSlotDTO> setNewTimeSlot(@RequestBody TimeRangeDTO timeRange) throws ParseException, DoctorNotFoundException, PatientNotFoundException {
        List<TimeSlotDTO> ret = new ArrayList<>();


        Date start = TimeSlotMapper.sd.parse(timeRange.getStartTime());
        Date end = TimeSlotMapper.sd.parse(timeRange.getEndTime());

        LocalDateTime startDate = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());

        int nbSlots = 0;

        LocalDateTime currentSlot = startDate;

        while(currentSlot.isBefore(endDate)) {
            currentSlot = startDate.plusMinutes(DURATION_TIME_SLOT * nbSlots);

            // Convert to Date object
            ZonedDateTime zdt = currentSlot.atZone(ZoneId.systemDefault());
            Date timeSlotDate = Date.from(zdt.toInstant());
            // Add in a new TimeSlot Object
            TimeSlotDTO newSlot = new TimeSlotDTO(timeRange.getIdDoctor(), null, TimeSlotMapper.sd.format(timeSlotDate));
        
            TimeSlot saved = timeSlotDAO.save(timeSlotMapper.toEntity(newSlot));
            ret.add(timeSlotMapper.toDTO(saved));
            nbSlots++;
        }

        return ret;
    }


    @GetMapping("/{id}")
    TimeSlotDTO one(@PathVariable Long id) throws TimeSlotNotFoundException {
        TimeSlot appointment = timeSlotDAO.findById(id).orElseThrow(() -> new TimeSlotNotFoundException(id));
        return timeSlotMapper.toDTO(appointment);
    }

    @PutMapping("/{id}")
    TimeSlotDTO replacePatient(@RequestBody TimeSlot newTimeSlot, @PathVariable Long id) {
        TimeSlot updatedTimeSlot = timeSlotDAO.findById(id)
                .map(TimeSlot -> {
                    TimeSlot.setPatient(newTimeSlot.getPatient());
                    TimeSlot.setDoctor(newTimeSlot.getDoctor());
                    TimeSlot.setDate(newTimeSlot.getDate());
                    return timeSlotDAO.save(TimeSlot);
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
