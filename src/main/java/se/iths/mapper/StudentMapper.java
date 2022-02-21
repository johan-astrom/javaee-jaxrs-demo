package se.iths.mapper;

import se.iths.entity.Student;
import se.iths.entity.StudentDto;

public class StudentMapper {

    protected static StudentDto studentToStudentDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber()
        );
    }

}
