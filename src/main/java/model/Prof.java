package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Prof")
@Data
public class Prof extends Users {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "User_Creneau")
    private Set<Creneau> creneaux;
    @OneToMany(mappedBy = "prof" )
    private Set<Rdv> rdv;
}
