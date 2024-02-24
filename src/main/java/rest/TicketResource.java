package rest;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/pet")
@Produces({"application/json"})
public class PetResource {

    @GET
    @Path("/{petId}")
    public Pet getPetById(@PathParam("petId") Long petId)  {
        // return ticket
        return new Pet();
    }

    @GET
    @Path("/")
    public Pet getPet(Long petId)  {
        return new Pet();
    }

    @POST
    @Consumes("application/json")
    public Response addPet(
            @Parameter(description = "Ticket object that needs to be added to the store", required = true) Pet pet) {
        // add ticket
        return Response.ok().entity("SUCCESS").build();
    }
}
