package metier;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Worker extends User {
	private Job job;


	private String name;

	@Transient
	public Job getJob() {
		return job;
	}
	
	@Transient
	public void setJob(Job job) {
		this.job = job;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
