package fr.istic.sir.rest;

import fr.istic.sir.entities.User;
import fr.istic.sir.jpa.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> index() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createQuery("select u from User u");

        return query.getResultList();
    }

}
