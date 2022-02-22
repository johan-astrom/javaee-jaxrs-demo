package se.iths.mapper;

import se.iths.dto.StudentSubjectDto;
import se.iths.dto.TeacherSubjectDto;
import se.iths.entity.Student;
import se.iths.dto.SubjectStudentDto;
import se.iths.entity.Subject;
import se.iths.dto.SubjectGetDto;

import java.util.ArrayList;
import java.util.List;

public class SubjectMapper {

    public static SubjectGetDto subjectToSubjectDto(Subject subject) {
        List<SubjectStudentDto> subjectStudentDtos = new ArrayList<>();
        for (Student student : subject.getStudents()) {
            subjectStudentDtos.add(StudentMapper.studentToStudentDto(student));
        }

        return new SubjectGetDto(
                subject.getId(),
                subject.getName(),
                subjectStudentDtos,
                TeacherMapper.teacherToTeacherDto(subject.getTeacher())
        );
    }

    protected static StudentSubjectDto subjectToStudentSubjectDto(Subject subject){
        return new StudentSubjectDto(subject.getId(),
                subject.getName(),
                TeacherMapper.teacherToTeacherDto(subject.getTeacher()));
    }

    protected static TeacherSubjectDto subjectToTeacherSubjectDto(Subject subject){
        return new TeacherSubjectDto(subject.getId(),
                subject.getName());
    }
}
