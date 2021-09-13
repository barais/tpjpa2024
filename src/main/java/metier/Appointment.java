package metier;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "getAllApointment",query = "select distinct e from Appointment as e")

})
public class Appointment implements Serializable {

    private Long id;

    private String sujet;

    private Date dateRdv;

    private Worker worker;

    private User user;


    public Appointment() {}
    
    public Appointment(String s, Date d, User u, Worker w) {
    	this.sujet=s;
    	this.dateRdv=d;
    	this.user=u;
    	this.worker=w;
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }


    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @ManyToOne
    public Worker getWorker(){
        return  this.worker;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
