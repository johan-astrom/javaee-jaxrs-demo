package se.iths.mapper;

import se.iths.entity.Student;
import se.iths.entity.StudentDto;
import se.iths.entity.Subject;
import se.iths.entity.SubjectDto;

import java.util.ArrayList;
import java.util.List;

public class SubjectMapper {

    public static SubjectDto subjectToSubjectDto(Subject subject) {
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : subject.getStudents()) {
            studentDtos.add(StudentMapper.studentToStudentDto(student));
        }

        return new SubjectDto(
                subject.getId(),
                subject.getName(),
                studentDtos,
                TeacherMapper.teacherToTeacherDto(subject.getTeacher())
        );
    }
}
