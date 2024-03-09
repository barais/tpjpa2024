package domaine;

import jakarta.persistence.*;
import utils.State;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Tickets implements Serializable {
    private Long id;
    private String content;
    private String title;
    private List<Tags> tags;
    private List<Comments> comments;
    private State state=State.OPEN;
    private Date milistone;
    private Date created_at;
    private Date updated_at;
    private Projects project;
    private Utilisateur created_by;
    private List<Utilisateur> assign_to;

    public Tickets(){

    }

    public Tickets(String content, String title, Utilisateur created_by, Projects project) {
        this.content = content;
        this.title = title;
        this.created_by = created_by;
        this.project = project;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    @OneToMany(mappedBy = "ticket")
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @ManyToMany(mappedBy = "tickets")
    public List<Tags> getTags() {
        return tags;
    }

    @Enumerated(EnumType.STRING)
    public State getState() {
        return state;
    }

    public Date getMilistone() {
        return milistone;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    @ManyToOne
    public Projects getProject() {
        return project;
    }

    @OneToOne
    public Utilisateur getCreated_by() {
        return created_by;
    }

    @ManyToMany
    public List<Utilisateur> getAssign_to() {
        return assign_to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setMilistone(Date milistone) {
        this.milistone = milistone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public void setCreated_by(Utilisateur created_by) {
        this.created_by = created_by;
    }

    public void setAssign_to(List<Utilisateur> user) {
        this.assign_to = user;
    }
}
