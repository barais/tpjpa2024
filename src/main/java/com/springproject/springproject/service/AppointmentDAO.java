package com.springproject.springproject.service;

import com.springproject.springproject.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, Long> {
}
