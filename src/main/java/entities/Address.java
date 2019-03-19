package entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    private long id;

    private String location;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
