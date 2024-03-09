package domaine;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Tags implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Utilisateur created_by;
    private List<Tickets> tickets;
    private Date created_at;

    public Tags(){

    }

    public Tags(String name, String description, Utilisateur created_by) {
        this.name = name;
        this.description = description;
        this.created_by = created_by;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @OneToOne
    public Utilisateur getCreated_by() {
        return created_by;
    }

    @ManyToMany
    public List<Tickets> getTickets() {
        return tickets;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_by(Utilisateur created_by) {
        this.created_by = created_by;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }
}
