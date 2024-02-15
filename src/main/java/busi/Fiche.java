package busi;

import jakarta.persistence.*;

/**
 * Classe abstraite représentant une fiche dans le système de gestion de tickets.
 * @autor nana
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Fiche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    // Autres attributs et méthodes communs à toutes les fiches
}
