package com.springproject.springproject.service;

import com.springproject.springproject.domain.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface SpecialisationDAO extends JpaRepository<Specialisation, Long> {

}
