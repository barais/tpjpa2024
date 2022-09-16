package service;

import domain.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDAO extends JpaRepository<Specialisation, Long> {
}
