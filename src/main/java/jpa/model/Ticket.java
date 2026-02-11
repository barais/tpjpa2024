package jpa.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Ticket implements Serializable {
    private Long ticketId;

    private String numeroPlace;

    @Enumerated(EnumType.STRING)
    private StatutTicketEnum statut;

    private Double prixUnitaire;

    private LocalDateTime dateAchat;

    private LocalDateTime dateAnnulation;

    private LocalDateTime dateRemboursement;

    private Concert concert;

    // region Generated code

    public Ticket() {
    }

    public Ticket(String numeroPlace, Double prixUnitaire, Concert concert) {
        this.numeroPlace = numeroPlace;
        this.prixUnitaire = prixUnitaire;
        this.concert = concert;

    }

    public LocalDateTime getDateRemboursement() {
        return dateRemboursement;
    }

    public void setDateRemboursement(LocalDateTime dateRemboursement) {
        this.dateRemboursement = dateRemboursement;
    }

    public LocalDateTime getDateAnnulation() {
        return dateAnnulation;
    }

    public void setDateAnnulation(LocalDateTime dateAnnulation) {
        this.dateAnnulation = dateAnnulation;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public StatutTicketEnum getStatut() {
        return statut;
    }

    public void setStatut(StatutTicketEnum statut) {
        this.statut = statut;
    }

    public String getNumeroPlace() {
        return numeroPlace;
    }

    public void setNumeroPlace(String numeroPlace) {
        this.numeroPlace = numeroPlace;
    }

    @Id
    @GeneratedValue
    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @ManyToOne
    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", numeroPlace='" + numeroPlace + '\'' +
                ", statut=" + statut +
                ", prixUnitaire=" + prixUnitaire +
                ", dateAchat=" + dateAchat +
                ", dateAnnulation=" + dateAnnulation +
                ", dateRemboursement=" + dateRemboursement +
                '}';
    }
    // endregion
}
