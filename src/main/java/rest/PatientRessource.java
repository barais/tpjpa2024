package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import dao.PatientDAO;
import entities.Patient;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/patient")
@Produces({"application/json"})
public class PatientRessource {

    PatientDAO patientDAO = new PatientDAO();

    @GET
    @Path("/{patientID}")
    public Patient getPatientById(@PathParam("patientID") Long patientID)  {
        return patientDAO.findOne(patientID);
    }

    @POST
    @Consumes("application/json")
    public Response addPatient(
            @Parameter(description = "Patient object that needs to be added to the store", required = true) Patient patient) {
        //TODO handle already patient in databse
        patientDAO.save(patient);
        return Response.ok().entity("SUCCESS ADD").build();
    }

    @DELETE
    @Path("/{patientID}")
    @Consumes("application/json")
    public Response removePatient(@PathParam("patientID") Long patientID) {
        if(patientDAO.findOne(patientID)==null) return Response.status(404).build();
        patientDAO.deleteById(patientID);
        return Response.ok().entity("SUCCESS DELETE").build();
    }

}