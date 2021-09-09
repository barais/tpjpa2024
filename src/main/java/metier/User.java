package metier;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User {
	private Long id;
	private String name;
	private String dateNaissance;//'jj/mm/yyyy'

	private List<Appointment> appointments;

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public List<Appointment> getAppointments() {
		return appointments;
	}

	@Transient
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
