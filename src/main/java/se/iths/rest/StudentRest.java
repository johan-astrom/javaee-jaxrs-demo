package se.iths.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import se.iths.dto.StudentGetDto;
import se.iths.entity.Student;
import se.iths.exception.*;
import se.iths.service.StudentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Objects;

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
        List<StudentGetDto> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(new StudentErrorMessage("No students found in the database.", Response.Status.NOT_FOUND))
                    .build();
        } else {
            return Response.ok(students).build();
        }

    }

    @GET
    @Path("{id}")
    public Response getStudentById(@PathParam("id") Long id) throws JsonProcessingException {
        StudentGetDto student = studentService.getStudentById(id);
        if (student == null){
            throw new EntityNotFoundWebException("No student with the specified id  found.", Response.Status.NO_CONTENT);
        }else{
            var filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("studentSubjectFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "teacher"));
            ObjectMapper om = new ObjectMapper();
            om.setFilters(filterProvider);

            return Response.ok(om.writeValueAsString(student)).build();
        }
    }

    @GET
    @Path("getByLastName/{lastName}")
    public Response getStudentByLastName(@PathParam("lastName") String lastName) {
        try {
            Student student = studentService.getStudentByLastName(lastName);
            if (student == null) {
                throw new EntityNotFoundWebException("No student with the specified last name found.", Response.Status.NO_CONTENT);
            } else {
                return Response.ok(student).build();
            }
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultWebException("Several students with the same last name found.", Response.Status.NO_CONTENT);
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

    @PATCH
    @Path("addSubject")
    public Response addSubject(@QueryParam("studentId") Long studentId, @QueryParam("subjectId") Long subjectId){
        studentService.addStudentSubject(studentId, subjectId);
        return Response.noContent().build();
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
