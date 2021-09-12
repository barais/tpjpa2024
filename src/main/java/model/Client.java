package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Client")
@Data
public class Client extends Users {
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Rdv> rdv;
}
