package se.iths.mapper;

import se.iths.dto.StudentSubjectDto;
import se.iths.dto.TeacherGetDto;
import se.iths.dto.TeacherSubjectDto;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.dto.SubjectTeacherDto;

import java.util.ArrayList;
import java.util.List;

public class TeacherMapper {

    protected static SubjectTeacherDto teacherToTeacherDto(Teacher teacher) {
        return new SubjectTeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getPhoneNumber()
        );
    }

    public static TeacherGetDto teacherToTeacherGetDto(Teacher teacher){
        List<TeacherSubjectDto> subjectDtos = new ArrayList<>();
        for (Subject subject : teacher.getSubjects()){
            subjectDtos.add(SubjectMapper.subjectToTeacherSubjectDto(subject));
        }

        return new TeacherGetDto(teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getPhoneNumber(),
                subjectDtos);
    }
}
