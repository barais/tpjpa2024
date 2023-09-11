package jpa;

import domain.Meeting;
import domain.Office;
import domain.Room;
import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.CRUD.MeetingCRUD;
import jpa.CRUD.RoomCRUD;
import jpa.CRUD.UserCRUD;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RDVManager {
    private EntityManager manager;
    private Scanner sc;
    private User currentUser;
    private UserCRUD userCRUD;
    private RoomCRUD roomCRUD;
    private MeetingCRUD meetingCRUD;

    public RDVManager(EntityManager manager) {
        this.manager = manager;
        sc = new Scanner(System.in);
        userCRUD = new UserCRUD(manager);
        roomCRUD = new RoomCRUD(manager);
        meetingCRUD = new MeetingCRUD(manager);
    }

    public static void main(String[] args) {
        EntityManager manager = EntityManagerHelper.getEntityManager();
        RDVManager rdvManager = new RDVManager(manager);

        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        try {

            // Connection ou inscription d'un utilisateur
            rdvManager.connectUser();

            // Menu principal
            rdvManager.mainMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println(".. done");
    }

    private void connectUser() {
        // Connection ou inscription d'un utilisateur
        while (currentUser == null) {
            String username;
            String password;
            System.out.println("Login or register :");
            System.out.println("1. Login\n2. Register\n");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Username :");
                    username = sc.next();
                    System.out.println("Password :");
                    password = sc.next();
                    if (userCRUD.login(username, password)) {
                        currentUser = userCRUD.getUser(username, password);
                    } else {
                        System.out.println("Wrong username or password");
                    }
                    break;
                case 2:
                    System.out.println("First name :");
                    String firstName = sc.next();
                    System.out.println("Last name :");
                    String lastName = sc.next();
                    System.out.println("Username :");
                    username = sc.next();
                    System.out.println("Password :");
                    password = sc.next();
                    try {
                        userCRUD.register(firstName, lastName, username, password);
                        currentUser = userCRUD.getUser(username, password);
                    } catch (Exception e) {
                        System.out.println("User already exists");
                    }
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private void mainMenu() {
        int choice = 0;
        while (choice != 3) {
            System.out.println("1. Schedule manager\n2. Your Account\n3. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    scheduleManager();
                    break;
                case 2:
                    accountManager();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private void accountManager() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("1. Change password\n2. Delete account\n3. Select office \n4. Back");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("New password :");
                    String password = sc.next();
                    userCRUD.updatePassword(currentUser.getUsername(), password);
                    break;
                case 2:
                    userCRUD.deleteUser(currentUser.getUsername(), currentUser.getPassword());
                    currentUser = null;
                    connectUser();
                    break;
                case 3:
                    List<Office> availableOffices = roomCRUD.selectAvailableOffices();
                    System.out.println("Available offices :");
                    for (Office office : availableOffices) {
                        System.out.println(office.getRoomNumber());
                    }
                    System.out.println("Select office (-1 to quit) :");
                    int officeNumber = sc.nextInt();
                    if (officeNumber == -1) {
                        break;
                    }
                    try {
                        roomCRUD.assignOffice(officeNumber, currentUser);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private void scheduleManager() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("1. Create meeting\n2. Delete meeting\n3. Join meeting\n4. Back");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Select start date (dd/MM/yyyy hh:mm) :");
                    String startDateString = sc.next();
                    LocalDateTime startDate = LocalDateTime.parse(startDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
                    System.out.println("Select end date (dd/MM/yyyy hh:mm) :");
                    String endDateString = sc.next();
                    LocalDateTime endDate = LocalDateTime.parse(endDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
                    System.out.println("Available rooms :");
                    List<Room> availableRooms = meetingCRUD.selectAvailableRooms(endDate);
                    for (Room room : availableRooms) {
                        System.out.println(room.getRoomNumber());
                    }
                    System.out.println("Select room (-1 to quit) :");
                    int roomNumber = sc.nextInt();
                    if (roomNumber == -1) {
                        break;
                    }
                    System.out.println("Label :");
                    String label = sc.next();
                    try {
                        meetingCRUD.createMeeting(label, startDate, endDate, currentUser, roomCRUD.selectRoom(roomNumber));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    List<Meeting> meetings = meetingCRUD.selectMeetingByUser(currentUser);
                    if (meetings.isEmpty()) {
                        System.out.println("No meetings");
                        break;
                    }
                    System.out.println("Select meeting to delete (-1 to quit) :");
                    for (Meeting meeting : meetings) {
                        System.out.println(meeting.getLabel());
                    }
                    int meetingNumber = sc.nextInt();
                    if (meetingNumber == -1) {
                        break;
                    }
                    try {
                        meetingCRUD.deleteMeeting(meetings.get(meetingNumber));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    meetings = meetingCRUD.selectMeetings();
                    if (meetings.isEmpty()) {
                        System.out.println("No meetings");
                        break;
                    }
                    for (Meeting meeting : meetings) {
                        System.out.println(meeting.getLabel());
                    }
                    System.out.println("Select meeting to join (-1 to quit) :");
                    meetingNumber = sc.nextInt();
                    if (meetingNumber == -1) {
                        break;
                    }
                    try {
                        meetingCRUD.joinMeeting(currentUser, meetings.get(meetingNumber));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}
