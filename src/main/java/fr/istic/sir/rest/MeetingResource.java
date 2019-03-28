package fr.istic.sir.rest;

import fr.istic.sir.entities.Meeting;
import fr.istic.sir.entities.User;
import fr.istic.sir.entities.repository.MeetingRepository;
import fr.istic.sir.entities.repository.UserRepository;
import fr.istic.sir.jpa.Utils;
import fr.istic.sir.repositories.Repository;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Optional;

@Path("/meetings")
public class MeetingResource {

    private final Repository<Meeting> repository;
    private final Repository<User> userRepository;

    public MeetingResource() {
        this.repository = new MeetingRepository();
        this.userRepository = new UserRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Meeting> index() {
        return repository.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Meeting store(JSONObject request) throws Exception {
        String creatorEmail = request.getString("email");
        String title = request.getString("title");
        String summary = request.getString("summary");
        String startAt = request.getString("startAt");
        String endAt = request.getString("endAt");

        Optional<User> creator = userRepository.findById(creatorEmail);
        if (!creator.isPresent()) return null;

        Meeting meeting = new Meeting(title, summary, Utils.parseToDate(startAt), Utils.parseToDate(endAt));
        meeting.setCreator(creator.get());

        repository.save(meeting);

        return meeting;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Meeting show(@PathParam("id") Long id) {
        Optional<Meeting> meeting = repository.findById(id);

        return meeting.orElse(null);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Meeting update(@PathParam("id") Long id, JSONObject request) throws Exception {
        Optional<Meeting> opt = repository.findById(id);
        if (!opt.isPresent()) return null;

        String title = request.getString("title");
        String summary = request.getString("summary");
        String startAt = request.getString("startAt");
        String endAt = request.getString("endAt");

        Meeting meeting = opt.get();
        meeting.setTitle(title);
        meeting.setSummary(summary);
        meeting.setStartAt(Utils.parseToDate(startAt));
        meeting.setEndAt(Utils.parseToDate(endAt));

        repository.update(meeting);

        return meeting;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Meeting destroy(@PathParam("id") Long id) {
        Optional<Meeting> opt = repository.findById(id);
        if (!opt.isPresent()) return null;

        return repository.delete(opt.get());
    }
}
