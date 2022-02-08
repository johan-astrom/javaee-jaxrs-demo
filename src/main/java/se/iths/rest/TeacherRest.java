package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.exception.EntityNotFoundServiceException;
import se.iths.exception.EntityNotFoundWebException;
import se.iths.exception.StudentErrorMessage;
import se.iths.service.TeacherService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("teachers")
public class TeacherRest {

    @Inject
    private TeacherService teacherService;

    @GET
    @Produces("application/json")
    public Response getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        if (teachers.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(new StudentErrorMessage("No teachers found in the database.", 204))
                    .build();
        } else {
            return Response.ok(teachers).build();
        }

    }

    @GET
    @Path("{id}")
    public Response getTeacherById(@PathParam("id") Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher == null) {
            throw new EntityNotFoundWebException("No teacher with the specified id found.", Response.Status.NO_CONTENT);
        } else {
            return Response.ok(teacher).build();
        }
    }

    @GET
    @Path("getByLastName/{lastName}")
    public Response getTeacherByLastName(@PathParam("lastName") String lastName) {
        Teacher teacher = teacherService.getTeacherByLastName(lastName);
        if (teacher == null) {
            throw new EntityNotFoundWebException("No teacher with the specified last name found.", Response.Status.NO_CONTENT);
        } else {
            return Response.ok(teacher).build();
        }
    }

    @POST
    public Response createTeacher(Teacher teacher, @Context UriInfo uriInfo) {
        Long teacherId = teacherService.createTeacher(teacher).getId();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(String.valueOf(teacherId));
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        try {
            return Response.ok(teacherService.updateTeacher(id, teacher)).build();
        } catch (EntityNotFoundServiceException e) {
            throw new EntityNotFoundWebException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeacher(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
            return Response.noContent().build();
        } catch (EntityNotFoundServiceException e) {
            throw new EntityNotFoundWebException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }
}