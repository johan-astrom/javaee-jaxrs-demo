package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.EntityNotFoundWebException;
import se.iths.service.SubjectService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("subjects")
public class SubjectRest {

    @Inject
    private SubjectService subjectService;

    @GET
    @Path("{id}")
    public Response getSubjectById(@PathParam("id") Long id) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            throw new EntityNotFoundWebException("No subject with the specified id found.", Response.Status.NO_CONTENT);
        } else {
            return Response.ok(subject).build();
        }
    }

    @POST
    public Response createSubject(Subject subject, @Context UriInfo uriInfo) {
        Long subjectId = subjectService.createSubject(subject).getId();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(String.valueOf(subjectId));
        return Response.created(uriBuilder.build()).build();
    }
}
