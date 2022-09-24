package com.springproject.springproject.service;

import com.springproject.springproject.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotDAO extends JpaRepository<TimeSlot, Long> {
}
