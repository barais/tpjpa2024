package jpa;

import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import entities.Meeting;
import entities.Survey;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Faker faker = new Faker();
		try {
//			User user = new User(faker.internet().emailAddress(), faker.name().lastName(), faker.name().firstName());
//			Lorem lorem = faker.lorem();
//			Meeting userMeeting = new Meeting(lorem.sentence(), lorem.paragraph(), new Date(), faker.date().future(2, TimeUnit.HOURS));
//			user.setSurveys(Collections.singletonList(userMeeting));
//			Survey userSurvey = new Survey();
//			manager.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	}


}
