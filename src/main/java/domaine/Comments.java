package domaine;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
public class Comments implements Serializable {

    private Long id;
    private String content;
    private Tickets ticket;
    private Utilisateur created_by;
    private Date created_at;
    private Date updated_at;

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Comments(String content, Tickets ticket, Utilisateur created_by) {
        this.content = content;
        this.ticket = ticket;
        this.created_by = created_by;
    }

    public Comments() {

    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @ManyToOne
    public Tickets getTicket() {
        return ticket;
    }

    @ManyToOne
    public Utilisateur getCreated_by() {
        return created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }

    public void setCreated_by(Utilisateur created_by) {
        this.created_by = created_by;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
