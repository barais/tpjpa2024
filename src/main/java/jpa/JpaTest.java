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
import jpa.Service.RepositoryRequests;

public class JpaTest {

	private EntityManager manager;

	private RepositoryRequests repoReq;

    public JpaTest(EntityManager manager) {
		this.manager = manager;
		this.repoReq = new RepositoryRequests(manager);
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
		Etudiant e1 = new Etudiant("Harry Potter");
		Etudiant e2 = new Etudiant("Hermione Granger");
		Etudiant e3 = new Etudiant("Ron Weaseley");
		Etudiant e4 = new Etudiant("Dobby");
		manager.persist(e1);
		manager.persist(e2);
		manager.persist(e3);
		manager.persist(e4);
	}

	private void test2(){
		Professeur e1 = new Professeur("Godric Gryffondor");
		Professeur e2 = new Professeur("Helga Poufsouffle");
		Professeur e3 = new Professeur("Rowena Serdaigle");
		Professeur e4 = new Professeur("Salazar Serpentar");
		manager.persist(e1);
		manager.persist(e2);
		manager.persist(e3);
		manager.persist(e4);
	}
	
	private void test3(){
	}



	private void result(){
		
		List<Etudiant> result = repoReq.getAllEtudiants();
		Iterator<Etudiant> it = result.iterator();
		System.out.println("Test 1 : Etudiants");
		while(it.hasNext()){
			System.out.println(it.next());
		}
		List<Professeur> listprof = repoReq.getAllProfesseurs();
		Iterator<Professeur> itprof = listprof.iterator();
		System.out.println("Test 2 : Etudiants");
		while(itprof.hasNext()){
			System.out.println(itprof.next());
		}
	}
}
