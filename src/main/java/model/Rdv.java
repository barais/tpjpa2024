package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Rdv implements Serializable {
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
