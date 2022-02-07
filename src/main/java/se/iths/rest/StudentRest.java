package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/students")
public class StudentRest {

    @Inject
    private StudentService studentService;

    @GET
    public Response getAllStudents() {
        return Response.ok(studentService.getAllStudents()).build();
    }

    @GET
    @Path("{id}")
    public Response getStudentById(@PathParam("id") Long id) {
        return Response.ok(studentService.getStudentById(id)).build();
    }

    @GET
    @Path("getByLastName/{lastName}")
    public Response getStudentByLastName(@PathParam("lastName") String lastName){
        return Response.ok(studentService.getStudentByLastName(lastName)).build();
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
        return Response.ok(studentService.updateStudent(id, student)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.noContent().build();
    }


}
