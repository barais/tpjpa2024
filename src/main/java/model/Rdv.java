package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rdv {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    Client client;
    @ManyToOne
    Prof prof;
    @ManyToOne
    Creneau creneau;

}
