package jpa;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javassist.tools.Dump;
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
		test2();
		System.out.println("Fin des tests");
	}

	private void test1(){
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

	private void test2(){
		Professeur e1 = new Professeur("Helene Tuffigo");
		Professeur e2 = new Professeur("Frank Poirier");
		Professeur e3 = new Professeur("Caroline Larboulette");
		Professeur e4 = new Professeur("Nicolas Courti");
		Professeur e5 = new Professeur("Luc Courti");
		manager.persist(e1);
		manager.persist(e2);
		manager.persist(e3);
		manager.persist(e4);
		manager.persist(e5);
	}
	
	private void test3(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Etudiant> query = criteriaBuilder.createQuery(Etudiant.class);
		Root<Etudiant> from = query.from(Etudiant.class);
		query.select(from.get("name"));
		List<Etudiant> result = manager.createQuery(query).getResultList();
		Iterator<Etudiant> it = result.iterator();
	}



	private void result(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Etudiant> query = criteriaBuilder.createQuery(Etudiant.class);
		Root<Etudiant> from = query.from(Etudiant.class);
		query.select(from);
		List<Etudiant> result = manager.createQuery(query).getResultList();
		Iterator<Etudiant> it = result.iterator();
		System.out.println("Test 1 : Etudiants");
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
