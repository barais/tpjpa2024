package jpa.CRUD;

import domain.Meeting;
import domain.Room;
import domain.User;
import domain.UserMeeting;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingCRUD {

    private final EntityManager manager;

    public MeetingCRUD(EntityManager manager) {
        this.manager = manager;
    }

    public List<Room> selectAvailableRooms(LocalDateTime end) {
        TypedQuery<Room> selectUnavailableRooms = manager.createQuery("SELECT a.room FROM Meeting a WHERE a.timeSlotStart < ?1", Room.class);
        selectUnavailableRooms.setParameter(1, end);
        TypedQuery<Room> selectAvailableRooms = manager.createQuery("SELECT a FROM MeetingRoom a WHERE a NOT IN ?1", Room.class);
        selectAvailableRooms.setParameter(1, selectUnavailableRooms.getResultList());
        return selectAvailableRooms.getResultList();
    }

    public void createMeeting(String label, LocalDateTime timeSlotStart, LocalDateTime timeSlotEnd, User host, Room room) {
        if (selectMeeting(label, timeSlotStart, timeSlotEnd, host, room) != null) {
            throw new IllegalArgumentException("Meeting already exists");
        }
        manager.persist(new domain.Meeting(label, timeSlotStart, timeSlotEnd, host, room));
    }

    public Meeting selectMeeting(String label, LocalDateTime timeSlotStart, LocalDateTime timeSlotEnd, User host, Room room) {
        TypedQuery<Meeting> selectMeeting = manager.createQuery("SELECT a FROM Meeting a WHERE a.label = ?1 AND a.timeSlotStart = ?2 AND a.timeSlotEnd = ?3 AND a.host.id = ?4 AND a.room.id = ?5", Meeting.class);
        selectMeeting.setParameter(1, label);
        selectMeeting.setParameter(2, timeSlotStart);
        selectMeeting.setParameter(3, timeSlotEnd);
        selectMeeting.setParameter(4, host.getId());
        selectMeeting.setParameter(5, room.getId());
        if (selectMeeting.getResultList().isEmpty()) {
            return null;
        }
        return selectMeeting.getSingleResult();
    }

    public List<Meeting> selectMeetings() {
        TypedQuery<Meeting> selectMeetings = manager.createQuery("SELECT a FROM Meeting a", Meeting.class);
        return selectMeetings.getResultList();
    }

    public List<Meeting> selectMeetingByUser(User user) {
        TypedQuery<Meeting> selectMeeting = manager.createQuery("SELECT a FROM Meeting a WHERE a.host = ?1", Meeting.class);
        selectMeeting.setParameter(1, user);
        return selectMeeting.getResultList();
    }

    public void deleteMeeting(Meeting meeting) {
        manager.remove(meeting);
    }

    public UserMeeting selectUserMeeting(Meeting meeting) {
        TypedQuery<UserMeeting> selectUserMeeting = manager.createQuery("SELECT a FROM UserMeeting a WHERE a.meeting = ?1", UserMeeting.class);
        selectUserMeeting.setParameter(1, meeting);
        if (selectUserMeeting.getResultList().isEmpty()) {
            return null;
        }
        return selectUserMeeting.getSingleResult();
    }

    public void joinMeeting(User user, Meeting meeting) {
        if (meeting.getHost().equals(user)) {
            throw new IllegalArgumentException("User is the host");
        }
        if (selectUserMeeting(meeting) != null) {
            throw new IllegalArgumentException("User already joined");
        }
        manager.persist(new UserMeeting(user, meeting));
    }
}
