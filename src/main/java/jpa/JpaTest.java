package jpa;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;
import jpa.Service.RdvService;

public class JpaTest {

	private EntityManager manager;

    public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
     * @param args
     */
    public static void main(String[] args) {
		// dev : hsql
		//mysql : mysql
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("mysql");
        EntityManager manager = factory.createEntityManager();
		RdvService rdvService = new RdvService(manager);
		JpaTest test = new JpaTest(manager);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
			test.test();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();
        test.result();
        manager.close();
        factory.close();
    }

	private void test(){
		System.out.println("Debut des tests");
		test1();
		System.out.println("Fin des tests");
	}

	private void test1(){
		System.out.println("Test 1 : Creation Etudiant et Professeurs");
		Etudiant e1 = new Etudiant("Justin bridou");
		Etudiant e2 = new Etudiant("Razer Xbox");
		Etudiant e3 = new Etudiant("Alexander pistoletov");
		Etudiant e4 = new Etudiant("Antoine Daniel");
		Etudiant e5 = new Etudiant("Le stagiaire");
		manager.persist(e1);
		manager.persist(e2);
		manager.persist(e3);
		manager.persist(e4);
		manager.persist(e5);
	}
	private void result(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
Root<Etudiant> from = query.from(Etudiant.class);


	}
}
