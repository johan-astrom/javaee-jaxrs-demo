package se.iths.mapper;

import se.iths.entity.Student;
import se.iths.entity.StudentDto;
import se.iths.entity.Teacher;
import se.iths.entity.TeacherDto;

public class TeacherMapper {

    protected static TeacherDto teacherToTeacherDto(Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getPhoneNumber()
        );
    }
}
