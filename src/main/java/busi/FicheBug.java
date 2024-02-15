package busi;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUG")
public class FicheBug extends Fiche {
    private String description;
    private String priorite;

}
