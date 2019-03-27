package fr.istic.sir.rest;

import fr.istic.sir.entities.User;
import fr.istic.sir.entities.repository.UserRepository;
import fr.istic.sir.repositories.Repository;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Optional;

@Path("/users")
public class UserResource {

    private Repository<User> repository;

    public UserResource() {
        this.repository = new UserRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> index() {
        return this.repository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User store(JSONObject req) throws JSONException {
        User user = new User(req.getString("email"), req.getString("lastName"), req.getString("firstName"));
        repository.save(user);

        return user;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User show(@PathParam("id") String id) {
        Optional<User> user = repository.findById(id);

        return user.orElse(null);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User update(@PathParam("id") String id, JSONObject req) throws JSONException {
        String lastName = req.getString("lastName");
        String firstName = req.getString("firstName");

        Optional<User> opt = repository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            user.setLastName(lastName);
            user.setFirstName(firstName);
            repository.update(user);

            return user;
        }

        return null;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User destroy(@PathParam("id") String id) {
        Optional<User> opt = repository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            System.out.println("is present: " + user);
            user = repository.delete(user);

            return user;
        }
        System.out.println("is not present");

        return null;
    }
}
