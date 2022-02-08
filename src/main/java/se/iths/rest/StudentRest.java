package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.StudentErrorMessage;
import se.iths.exception.EntityNotFoundServiceException;
import se.iths.exception.EntityNotFoundWebException;
import se.iths.service.StudentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@RequestScoped
@Path("/students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    private StudentService studentService;

    @GET
    @Produces("application/json")
    public Response getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(new StudentErrorMessage("No students found in the database.", 204))
                    .build();
        } else {
            return Response.ok(students).build();
        }

    }

    @GET
    @Path("{id}")
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            throw new EntityNotFoundWebException("No student with the specified id found.", Response.Status.NO_CONTENT);
        } else {
            return Response.ok(student).build();
        }
    }

    @GET
    @Path("getByLastName/{lastName}")
    public Response getStudentByLastName(@PathParam("lastName") String lastName) {
        Student student = studentService.getStudentByLastName(lastName);
        if (student == null) {
            throw new EntityNotFoundWebException("No student with the specified last name found.", Response.Status.NO_CONTENT);
        } else {
            return Response.ok(student).build();
        }
    }

    @POST
    public Response createStudent(Student student, @Context UriInfo uriInfo) {
        Long studentId = studentService.createStudent(student).getId();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(String.valueOf(studentId));
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        try {
            return Response.ok(studentService.updateStudent(id, student)).build();
        } catch (EntityNotFoundServiceException e) {
            throw new EntityNotFoundWebException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return Response.noContent().build();
        } catch (EntityNotFoundServiceException e) {
            throw new EntityNotFoundWebException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }


}
