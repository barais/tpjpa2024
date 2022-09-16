package service;

import domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Long> {

    /**
     * This method will allow us to get a patient in database using an ID
     * @param id
     * @return patient the patient with the id that match
     */
    public Patient findPatientById(Long id);

}
