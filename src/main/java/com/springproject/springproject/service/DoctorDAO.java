package com.springproject.springproject.service;

import com.springproject.springproject.domain.Doctor;
import com.springproject.springproject.domain.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorDAO extends JpaRepository<Doctor, Long> {

    @Query("select d from Doctor as d where d.specialisation = ?1")
    List<Doctor> getAllBySpecialities(Specialisation specialisation);

}
