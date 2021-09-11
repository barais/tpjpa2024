package jpa;

import java.sql.Date;

import javax.persistence.*;

import metier.*;

public class JpaTest {

	static EntityManager manager;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		manager = EntityManagerHelper.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();


		try {
			
			//testInitUser1();
			testInitRDV1();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		factory.close();
	}

	
	public static void testInitUser1() {
		User u1=new User("Georges",new Date(1265899000L));
		manager.persist(u1);
		
		User u2=new User("Albert", new Date(0));
		manager.persist(u2);
		
		Job job1=new Job("plombier",2000);
		manager.persist(job1);
		
		User w1=new Worker("Albert", new Date(0),job1,15);
		manager.persist(w1);
	}
	
	public static void testInitRDV1() {
		User u1=new User("Albert", new Date(1265899000L));
		manager.persist(u1);
		
		Job job1=new Job("plombier",2000);
		manager.persist(job1);
		
		Worker w1=new Worker("Albert", new Date(0),job1,15);
		manager.persist(w1);
		
		Appointment rdv1= new Appointment("J'ai mal o crane jpp", new Date(888888888L), u1, w1);
		manager.persist(rdv1);
	}

}
