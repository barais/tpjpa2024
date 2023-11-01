package Model;

import jakarta.persistence.*;

@Entity
@Table(name="rdv")
public class Rdv {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="titled")
	private String titled;

	@Column(name="professional")
	private Professional pro;

	@Column(name="student")
	private Student stu;

	@Column(name="slot")
	private Slot slot;

	public Rdv() {
	}

	public Rdv(Long id, String titled, Professional pro, Student stu, Slot slot) {
		this.id = id;
		this.titled = titled;
		this.pro = pro;
		this.stu = stu;
		this.slot = slot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitled() { 
		return titled;
	}

	public void setTitled(String titled) { 
		this.titled = titled;
	}

	public Professional getProfessional() {
		return pro;
	}

	public void setProfessional(Professional pro) {
		this.pro = pro;
	}

	@ManyToOne
	public Student getStudent() {
		return stu;
	}

	public void setStudent(Student stu) {
		this.stu = stu;
	}

	public Integer getSlot() { return slot.timeSlot; }

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

		@Override
	public String toString() {
		return "Rdv [id=" + id + ", titled=" + titled + ", professional=" + pro + ", student=" + stu + ", slot=" + slot + "]";
	}
}
