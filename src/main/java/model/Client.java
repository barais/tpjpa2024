package model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Client")
@Data
public class Client extends Users {
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Rdv> rdv;
}
