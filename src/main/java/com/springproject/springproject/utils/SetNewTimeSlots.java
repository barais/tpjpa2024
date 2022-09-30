package com.springproject.springproject.utils;

import com.springproject.springproject.domain.TimeSlot;
import com.springproject.springproject.dto.TimeRangeDTO;
import com.springproject.springproject.dto.TimeSlotDTO;
import com.springproject.springproject.exception.DoctorNotFoundException;
import com.springproject.springproject.exception.PatientNotFoundException;
import com.springproject.springproject.mapper.TimeSlotMapper;
import com.springproject.springproject.service.TimeSlotDAO;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SetNewTimeSlots {

    private static final int DURATION_TIME_SLOT = 30;

    public static List<TimeSlotDTO> setNewTimeSlots(TimeRangeDTO timeRangeDTO, TimeSlotDAO timeSlotDAO, TimeSlotMapper timeSlotMapper) throws DoctorNotFoundException, PatientNotFoundException, ParseException {
        List<TimeSlotDTO> ret = new ArrayList<>();


        Date start = TimeSlotMapper.sd.parse(timeRangeDTO.getStartTime());
        Date end = TimeSlotMapper.sd.parse(timeRangeDTO.getEndTime());

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
            TimeSlotDTO newSlot = new TimeSlotDTO(timeRangeDTO.getIdDoctor(), null, TimeSlotMapper.sd.format(timeSlotDate));

            TimeSlot saved = timeSlotDAO.save(timeSlotMapper.toEntity(newSlot));
            ret.add(timeSlotMapper.toDTO(saved));
            nbSlots++;
        }

        return ret;
    }
}