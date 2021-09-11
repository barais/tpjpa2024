package metier;

import java.sql.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Worker")
public class Worker extends User {
	private Job job;
	
	private int rate;//note moyenne [0,20] que les clients lui ont donnee

	public Worker() {
		super();
	}
	public Worker(String name, Date dateNaissance,Job job,int rate) {
		super(name,dateNaissance);
		this.job=job;
		this.rate=rate;
	}
	
	@ManyToOne//cardinalite cible puis celle du sujet
	public Job getJob() {
		return job;
	}
	
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
