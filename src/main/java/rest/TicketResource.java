package rest;

import models.Ticket;

import javax.ws.rs.*;

@Path("/tickets")

@Produces({"application/json"})
@Consumes({"application/json"})
public class TicketResource {

    @GET
    public Ticket hello() {
        return new Ticket();
    }
}