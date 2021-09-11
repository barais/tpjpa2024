package metier;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Worker extends User {
	private Job job;
	
	private int rate;//note moyenne [0,20] que les clients lui ont donnée

	public Worker() {
		super();
	}
	public Worker(String name, Date dateNaissance,Job job,int rate) {
		super(name,dateNaissance);
		this.job=job;
		this.rate=rate;
	}
	
	@Transient
	public Job getJob() {
		return job;
	}
	
	@Transient
	public void setJob(Job job) {
		this.job = job;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
