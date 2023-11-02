package Model;

import jakarta.persistence.*;

@Entity
@Table(name="rdv")
public class Rdv extends Slot {

	private Long id;

	private String titled;

	private Student stu;

	public Rdv() {
	}

	public Rdv(Long timeStart, Long timeEnd, Professional pro, String titled, Student stu) {
		super(timeStart, timeEnd, pro);
		this.titled = titled;
		this.stu = stu;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="titled")
	public String getTitled() { 
		return titled;
	}

	public void setTitled(String titled) { 
		this.titled = titled;
	}

	@ManyToOne
	public Student getStudent() {
		return stu;
	}

	public void setStudent(Student stu) {
		this.stu = stu;
	}

	@Override
	public String toString() {
		return "Rdv [id=" + id + ", titled=" + titled + ", student=" + stu + "]";
	}
}
