package jpa.CRUD;

import domain.Office;
import domain.Room;
import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RoomCRUD {
    private final EntityManager manager;

    public RoomCRUD(EntityManager manager) {
        this.manager = manager;
    }

    public List<Office> selectAvailableOffices() {
        TypedQuery<Office> selectAvailableOffices = manager.createQuery("Select a From Office a WHERE a.occupant IS NULL", Office.class);
        return selectAvailableOffices.getResultList();
    }

    public Office getOfficeFromOccupant(User occupant) {
        TypedQuery<Office> selectOffice = manager.createQuery("Select a From Office a WHERE a.occupant = ?1", Office.class);
        selectOffice.setParameter(1, occupant);
        if (selectOffice.getResultList().isEmpty()) {
            return null;
        }
        return selectOffice.getSingleResult();
    }

    public void assignOffice(int roomNumber, User occupant) {
        if (selectRoom(roomNumber) == null) {
            throw new IllegalArgumentException("Room does not exist");
        }
        TypedQuery<Office> selectOffice = manager.createQuery("Select a From Office a WHERE a.roomNumber = ?1", Office.class);
        selectOffice.setParameter(1, roomNumber);
        Office office = selectOffice.getSingleResult();
        if (office.getOccupant() != null) {
            throw new IllegalArgumentException("Office already occupied");
        }
        if (getOfficeFromOccupant(occupant) != null) {
            throw new IllegalArgumentException("User already has an office");
        }
        office.setOccupant(occupant);
    }

    public Room selectRoom(int roomNumber) {
        TypedQuery<Room> selectRoom = manager.createQuery("Select room From Room room WHERE room.roomNumber = ?1", Room.class);
        selectRoom.setParameter(1, roomNumber);
        if (selectRoom.getResultList().isEmpty()) {
            return null;
        }
        return selectRoom.getSingleResult();
    }

    public void createOffice(int capacity, int roomNumber) {
        if (selectRoom(roomNumber) != null) {
            throw new IllegalArgumentException("Room already exists");
        }
        manager.persist(new Office(capacity, roomNumber, null));
    }

    public void createMeetingRoom(int capacity, int roomNumber, String name) {
        if (selectRoom(roomNumber) != null) {
            throw new IllegalArgumentException("Room already exists");
        }
        manager.persist(new domain.MeetingRoom(capacity, roomNumber, name));
    }
}
