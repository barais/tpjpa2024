package model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Clients extends Users {
    @OneToMany
    List<Rdv> rdv;
}
