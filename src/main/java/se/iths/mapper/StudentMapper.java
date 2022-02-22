package se.iths.mapper;

import se.iths.dto.StudentGetDto;
import se.iths.dto.StudentSubjectDto;
import se.iths.entity.Student;
import se.iths.dto.SubjectStudentDto;
import se.iths.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    public static StudentGetDto studentToStudentGetDto(Student student){
        List<StudentSubjectDto> subjectDtos = new ArrayList<>();
        for (Subject subject : student.getSubjects()){
            subjectDtos.add(SubjectMapper.subjectToStudentSubjectDto(subject));
        }

        return new StudentGetDto(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber(),
                subjectDtos);
    }

    protected static SubjectStudentDto studentToStudentDto(Student student){
        return new SubjectStudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber()
        );
    }

}
