package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.StudentNotFoundServiceException;
import se.iths.exception.StudentNotFoundWebException;
import se.iths.service.StudentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("/students")
public class StudentRest {

    @Inject
    private StudentService studentService;

    @GET
    public Response getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students == null) {
            throw new StudentNotFoundWebException("No students stored in the database.", Response.Status.NO_CONTENT);
        } else {
            return Response.ok(students).build();
        }

    }

    @GET
    @Path("{id}")
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            throw new StudentNotFoundWebException("No student with the specified id found.", Response.Status.NO_CONTENT);
        } else {
            return Response.ok(student).build();
        }
    }

    @GET
    @Path("getByLastName/{lastName}")
    public Response getStudentByLastName(@PathParam("lastName") String lastName) {
        Student student = studentService.getStudentByLastName(lastName);
        if (student == null) {
            throw new StudentNotFoundWebException("No student with the specified last name found.", Response.Status.NO_CONTENT);
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
        }catch (StudentNotFoundServiceException e){
            throw new StudentNotFoundWebException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        try{
            studentService.deleteStudent(id);
            return Response.noContent().build();
        }catch (StudentNotFoundServiceException e){
            throw new StudentNotFoundWebException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }


}
