package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Specialisation implements Serializable {

    private String name;
    private Long id;

    public Specialisation() {}

    public Specialisation(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
