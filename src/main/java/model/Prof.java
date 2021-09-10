package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Prof")
@Data
public class Prof extends Users {

    @ManyToMany(mappedBy = "profs")
    private List<Creneau> creneaux;
    @OneToMany(mappedBy = "prof")
    private List<Rdv> rdv;
}
