package model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@DiscriminatorValue("Prof")
@Data
public class Prof extends Users {
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(name = "User_Creneau")
    @EqualsAndHashCode.Exclude
    private Set<Creneau> creneaux  = new HashSet<>();

    @OneToMany(mappedBy = "prof", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Rdv> rdv  = new HashSet<>();;
}
