package jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

	private EntityManager manager;

    public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
			test.createRdvs();

        
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        test.rdvsInfo();
        manager.close();
        factory.close();
    }

	private void createRdvs(){
		Professeur p1 = new Professeur("Leeroy Jenkins");
		Etudiant p2 = new Etudiant("Xavier Dang");
		Date d1 = new Date();
		Rdv r1 = new Rdv(p1, d1, p2);
		Rdv r2 = new Rdv(p1, d1, p2);
		manager.persist(p1);
		manager.persist(p2);
		manager.persist(r1);
		manager.persist(r2);
	}

	private void rdvsInfo(){
		List<Rdv> rdvs = manager.createQuery("Select a From Rdv a", Rdv.class).getResultList();
		System.out.println("Nombre de rdv actuel : " + rdvs.size());
		for (Rdv rdv : rdvs) {
		System.out.println(rdv);
		}
	}
}
