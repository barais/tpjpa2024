package rest;

import dao.LabelDao;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import models.Label;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/labels")
@Produces({"application/json"})
public class LabelResource {

    LabelDao dao = new LabelDao();
    @GET
    @Path("/{id}")
    public Label getLabelById(@PathParam("id") Long id)  {
        Label label;
        try{
            label = dao.findOne(id);
        } catch (Exception e) {
            label = null;
        }
        return label;
    }

    @POST
    @Consumes("application/json")
    public Response addLabel(

            @RequestBody(description = "Label object to add", required = true) Label label) {
        try {
            dao.save(label);
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erreur! Les données ne sont pas correctes : " + label.toString()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Une erreur inconnue s'est produite").build();
        }
        return Response.ok().entity("SUCCESS").build();
    }

    @GET
    public Collection<Label> getLabels() {
        return dao.findAll();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLabelById(@PathParam("id") Long id)  {
        try{
            dao.deleteById(id);
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erreur lors de la suppression").build();
        } catch (Exception e) {
            return Response.serverError().entity("Une erreur inconnue s'est produite").build();
        }
        return Response.ok().entity("SUCCESS").build();
    }

    @PATCH
    @Consumes("application/json")
    public Response updateLabel(@Parameter(description = "Label properties to modify", required = true) Label label)  {
        try {
            Label l = dao.findOne(label.getId());
            if(label.getNom() != null)
                l.setNom(label.getNom());

            if(label.getDescription() != null)
                l.setDescription(label.getDescription());

            if(label.getCouleur() != null)
                l.setCouleur(label.getCouleur());
            dao.update(l);
        } catch (PersistenceException e) {
            return Response.serverError().entity("Erreur! Les données ne sont pas correctes : " + label).build();
        } catch (Exception e) {
            return Response.serverError().entity("Une erreur inconnue s'est produite : "+e.getMessage()).build();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}
