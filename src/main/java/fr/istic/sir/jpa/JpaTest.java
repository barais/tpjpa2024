package fr.istic.sir.jpa;

import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import fr.istic.sir.entities.DateSurvey;
import fr.istic.sir.entities.Meeting;
import fr.istic.sir.entities.Survey;
import fr.istic.sir.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class JpaTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            Faker faker = new Faker();
            User user = new User(faker.internet().emailAddress(), faker.name().lastName(), faker.name().firstName());
            Lorem lorem = faker.lorem();
            Meeting userMeeting = new Meeting(lorem.sentence(), lorem.paragraph(), null, null);
            user.setMeetings(Collections.singletonList(userMeeting));
            userMeeting.setCreator(user);
            Survey userSurvey = new DateSurvey(faker.internet().url(), faker.date().future(2, TimeUnit.HOURS));
            userMeeting.setSurveys(Collections.singletonList(userSurvey));
            userSurvey.setMeeting(userMeeting);
            manager.persist(user);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        tx.commit();

        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
    }
}
