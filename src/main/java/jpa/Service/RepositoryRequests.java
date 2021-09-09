package jpa.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;

public class RepositoryRequests {

    private EntityManager manager;

   public List<Etudiant> getAllEtudiants(){
       CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Etudiant> query = criteriaBuilder.createQuery(Etudiant.class);
		Root<Etudiant> from = query.from(Etudiant.class);
		query.select(from);
		List<Etudiant> result = manager.createQuery(query).getResultList();
		return result;

    }

}