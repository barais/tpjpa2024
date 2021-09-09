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
    @OneToOne
    Clients client;
    @OneToOne
    Profs prof;
    @OneToOne
    Creneau creneau;

}
