package jpa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artiste {
    @Id
    @GeneratedValue
    private Long artisteId;

    private String nomScene;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private String nationalite;

    private String description;

    private int popularite;

    private String siteWeb;

    @ManyToMany(mappedBy = "artistes", fetch = FetchType.LAZY)
    private Set<Concert> concerts = new HashSet<>();

    // region Generated code


    public Set<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(Set<Concert> concerts) {
        this.concerts = concerts;
    }

    public Long getArtisteId() {
        return artisteId;
    }

    public void setArtisteId(Long artisteId) {
        this.artisteId = artisteId;
    }

    public String getNomScene() {
        return nomScene;
    }

    public void setNomScene(String nomScene) {
        this.nomScene = nomScene;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopularite() {
        return popularite;
    }

    public void setPopularite(int popularite) {
        this.popularite = popularite;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "artisteId=" + artisteId +
                ", nomScene='" + nomScene + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", nationalite='" + nationalite + '\'' +
                ", description='" + description + '\'' +
                ", popularite=" + popularite +
                ", siteWeb='" + siteWeb + '\'' +
                '}';
    }
    // endregion
}
