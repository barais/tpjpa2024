package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DiscriminatorFormula;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorFormula("CASE WHEN NAME IS NOT NULL THEN 1 ELSE 2 END")
public abstract class Room {
    protected Long id;
    protected int capacity;

    @Column(nullable = false, unique = true)
    int roomNumber;

    public Room() {
    }

    public Room(int capacity, int roomNumber) {
        this.capacity = capacity;
        this.roomNumber = roomNumber;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
