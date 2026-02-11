package jpa.model;

import jakarta.persistence.Entity;

@Entity
public class Organisateur extends Personne {

    private String nomStructure;

    private String numeroSiret;

    private String adresseSiege;

    private Boolean actif;

    // region Generated code
    public String getNomStructure() {
        return nomStructure;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    public String getNumeroSiret() {
        return numeroSiret;
    }

    public void setNumeroSiret(String numeroSiret) {
        this.numeroSiret = numeroSiret;
    }

    public String getAdresseSiege() {
        return adresseSiege;
    }

    public void setAdresseSiege(String adresseSiege) {
        this.adresseSiege = adresseSiege;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return "Organisateur{" +
                "nomStructure='" + nomStructure + '\'' +
                ", numeroSiret='" + numeroSiret + '\'' +
                ", adresseSiege='" + adresseSiege + '\'' +
                ", actif=" + actif +
                ", personneId=" + personneId +
                '}';
    }

    // endregion
}
