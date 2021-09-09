package model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Profs extends Users {

    @ManyToMany
    private List<Creneau> creneaux;
}
