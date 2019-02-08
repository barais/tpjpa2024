package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dietaries")
public class Dietary implements Serializable {
    private long id;

    private String name;

    public Dietary() {
    }

    public Dietary(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dietary dietary = (Dietary) o;
        return id == dietary.id &&
                name.equals(dietary.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("Dietary{id=%d, name='%s'}", id, name);
    }
}
