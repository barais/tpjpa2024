package metier;

import javax.persistence.Transient;

public class Worker extends User {
	private Job job;

	@Transient
	public Job getJob() {
		return job;
	}
	
	@Transient
	public void setJob(Job job) {
		this.job = job;
	}
	
}
