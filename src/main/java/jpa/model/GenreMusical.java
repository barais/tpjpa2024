package jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class GenreMusical {
    @GeneratedValue
    @Id
    private Long genreId;

    private String libelle;

    // region Generated code
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "GenreMusical{" +
                "genreId=" + genreId +
                ", libelle='" + libelle + '\'' +
                '}';
    }
    // endregion
}
