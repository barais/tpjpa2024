package com.springproject.springproject.service;

import com.springproject.springproject.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDAO extends JpaRepository<Doctor, Long> {


}
