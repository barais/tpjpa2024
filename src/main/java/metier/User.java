package metier;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries(
		{@NamedQuery (name ="bestWorker",query = "select e from Worker as e where e.rate = " +
				"(select max(e.rate) from User as e)"),
			@NamedQuery(name ="allWorker", query = "select e from Worker as e"),
		@NamedQuery(name ="allUser", query = "select e from User as e")}
)
public class User implements Serializable {
	private Long id;
	private String name;
	private Date dateNaissance; //'jj/mm/yyyy'

	private List<Appointment> appointments;

	public User() {}
	
	public User(String name){
		this.name = name;
	}
	
	public User(String name, Date dateNaissance) {
		this.name=name;
		this.dateNaissance=dateNaissance;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Column(length=20)
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

	@OneToMany(mappedBy="user")
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
