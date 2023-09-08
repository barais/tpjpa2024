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
        TypedQuery<Office> selectAvailableOffices =  manager.createQuery("Select a From Office a WHERE a.occupant IS NULL", Office.class);
        return selectAvailableOffices.getResultList();
    }

    public void assignOffice(int roomNumber, User occupant) {
        if(selectRoom(roomNumber) == null) {
            throw new IllegalArgumentException("Room does not exist");
        }
        TypedQuery<Office> selectOffice =  manager.createQuery("Select a From Office a WHERE a.roomNumber = ?1", Office.class);
        selectOffice.setParameter(1, roomNumber);
        Office office = selectOffice.getSingleResult();
        office.setOccupant(occupant);
    }

    public Room selectRoom(int roomNumber) {
        TypedQuery<Room> selectRoom =  manager.createQuery("Select a From Room a WHERE a.roomNumber = ?1", Room.class);
        selectRoom.setParameter(1, roomNumber);
        return selectRoom.getSingleResult();
    }
}
