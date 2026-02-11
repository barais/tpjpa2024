package jpa.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Concert implements Serializable {
    @Id
    @GeneratedValue
    private Long concertId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "artiste_genre",
            joinColumns = @JoinColumn(name = "concert_id"),
            inverseJoinColumns = @JoinColumn(name = "artiste_id")
    )
    private Set<Artiste> artistes = new HashSet<>();

    private String lieu;

    private LocalDateTime date;

    private String genre;

    private Long capacite;

    private Float popularite;

    private String description;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.PERSIST)
    private List<Ticket> tickets = new ArrayList<>();

    // region Generated code
    public Set<Artiste> getArtistes() {
        return artistes;
    }

    public void setArtistes(Set<Artiste> artistes) {
        this.artistes = artistes;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getCapacite() {
        return capacite;
    }

    public void setCapacite(Long capacite) {
        this.capacite = capacite;
    }

    public Float getPopularite() {
        return popularite;
    }

    public void setPopularite(Float popularite) {
        this.popularite = popularite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "concertId=" + concertId +
                ", artistes=" + artistes +
                ", lieu='" + lieu + '\'' +
                ", date=" + date +
                ", genre='" + genre + '\'' +
                ", capacite=" + capacite +
                ", popularite=" + popularite +
                ", description='" + description + '\'' +
                ", tickets=" + tickets +
                '}';
    }
    // endregion
}
