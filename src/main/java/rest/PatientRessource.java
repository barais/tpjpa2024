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
        //if(patientDAO.findOne(patient.getId()) != null) return Response.status(Response.Status.CONFLICT).build();
        patientDAO.save(patient);
        return Response.ok().entity("SUCCESS").build();
    }

    /*
    @POST
    @Consumes("application/json")
    public Response removePatient(
            @Parameter(description = "Patient object that need to be removed from the store", required = true) Patient patient) {
        PatientDAO.delete(entities.Patient);
        return Response.ok().entity("SUCCESS").build();
    }*/

}