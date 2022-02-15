package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.EntityNotFoundWebException;
import se.iths.service.SubjectService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

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

    @GET
    public Response getAllSubjects(){
        List<Subject> subjects = subjectService.getAllSubjects();
        if (subjects.isEmpty()){
            throw new EntityNotFoundWebException("No subjects found.", Response.Status.NO_CONTENT);
        }else {
            return Response.ok(subjects).build();
        }
    }

    @POST
    public Response createSubject(Subject subject, @Context UriInfo uriInfo, @QueryParam("teacherId") Long teacherId) {
        Long subjectId = subjectService.createSubject(subject, teacherId).getId();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(String.valueOf(subjectId));
        return Response.created(uriBuilder.build()).build();
    }
}
