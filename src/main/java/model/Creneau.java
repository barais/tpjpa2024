package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Creneau {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Date debut;
    private Date fin ;

    @ManyToMany
    private List<Prof> profs;

    @OneToMany(mappedBy = "creneau", cascade = CascadeType.PERSIST)
    private List<Rdv> rdv;
}
