package jpa.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Utilisateur extends Personne {
    private LocalDate dateInscription;

    private Double creditCompte;

    private Boolean preferenceNotificationEmail;

    private Boolean preferenceNotificationPush;

    // region Generated code
    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Double getCreditCompte() {
        return creditCompte;
    }

    public void setCreditCompte(Double creditCompte) {
        this.creditCompte = creditCompte;
    }

    public Boolean getPreferenceNotificationEmail() {
        return preferenceNotificationEmail;
    }

    public void setPreferenceNotificationEmail(Boolean preferenceNotificationEmail) {
        this.preferenceNotificationEmail = preferenceNotificationEmail;
    }

    public Boolean getPreferenceNotificationPush() {
        return preferenceNotificationPush;
    }

    public void setPreferenceNotificationPush(Boolean preferenceNotificationPush) {
        this.preferenceNotificationPush = preferenceNotificationPush;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "dateInscription=" + dateInscription +
                ", creditCompte=" + creditCompte +
                ", preferenceNotificationEmail=" + preferenceNotificationEmail +
                ", preferenceNotificationPush=" + preferenceNotificationPush +
                ", personneId=" + personneId +
                '}';
    }
    // endregion
}
