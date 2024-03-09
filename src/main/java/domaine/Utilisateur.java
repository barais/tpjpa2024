package domaine;

import jakarta.persistence.*;
import utils.Role;

import java.io.Serializable;
import java.util.List;

@Entity
public class Utilisateur implements Serializable {
    private Long id;
    private String username;
    private String name;
    private Role role;
    private List<Comments> comments;
    private List<Tickets> tickets;

    public Utilisateur(){

    }

    public Utilisateur(String username, String name, Role role) {
        this.username = username;
        this.name = name;
        this.role = role;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @ManyToMany(mappedBy = "assign_to")
    public List<Tickets> getTickets() {
        return tickets;
    }

    @OneToMany(mappedBy = "created_by")
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
