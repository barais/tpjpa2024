package Model;

public class Rdv {

	private Long id;
	private String titled;
	private Professional pro;
	private Slot slot;

	public Rdv() {
	}

	public Rdv(Long id, String titled, Professional pro, Slot slot) {
		this.id = id;
		this.titled = titled;
		this.pro = pro;
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

	public Integer getSlot() { return slot.timeSlot; }

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Rdv [id=" + id + ", titled=" + titled + ", professional=" + pro + ", slot=" + slot + "]";
	}
}
