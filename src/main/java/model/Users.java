package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public abstract class Users implements Serializable {

    @Id
    private String email;

    private String name;

    private String lastname;

    public Users() {
    }


}
