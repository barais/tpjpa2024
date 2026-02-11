package jpa.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Administrateur extends Personne {
    private LocalDate dateNomination;

    private Boolean actif;

    // region Generated code
    public LocalDate getDateNomination() {
        return dateNomination;
    }

    public void setDateNomination(LocalDate dateNomination) {
        this.dateNomination = dateNomination;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return "Administrateur{" +
                "dateNomination=" + dateNomination +
                ", actif=" + actif +
                ", personneId=" + personneId +
                '}';
    }

    // endregion
}
