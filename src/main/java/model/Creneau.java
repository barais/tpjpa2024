package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Creneau implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date debut;
    @Temporal(TemporalType.DATE)
    private Date fin ;

    @ManyToMany(mappedBy = "creneaux", cascade = CascadeType.ALL)
    private Set<Prof> profs;

    @OneToMany( mappedBy = "creneau", cascade = {CascadeType.ALL})
    private Set<Rdv> rdv;
}
