package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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

    @ManyToMany(mappedBy = "creneaux", cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @EqualsAndHashCode.Exclude
    private Set<Prof> profs = new HashSet<>();

    @OneToMany( mappedBy = "creneau", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private Set<Rdv> rdv = new HashSet<>();
}
