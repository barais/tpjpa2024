package domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class MeetingRoom extends Room{
    @Column(nullable = false)
    private String name;

    public MeetingRoom() {
    }

    public MeetingRoom(int capacity, int roomNumber, String name) {
        super(capacity, roomNumber);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
