package se.iths.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SubjectGetDto implements Serializable {
    private final Long id;
    private final String name;
    private final List<SubjectStudentDto> students;
    private final SubjectTeacherDto teacher;

    public SubjectGetDto(Long id, String name, List<SubjectStudentDto> students, SubjectTeacherDto teacher) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SubjectStudentDto> getStudents() {
        return students;
    }

    public SubjectTeacherDto getTeacher() {
        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectGetDto entity = (SubjectGetDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.students, entity.students) &&
                Objects.equals(this.teacher, entity.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, students, teacher);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "students = " + students + ", " +
                "teacher = " + teacher + ")";
    }
}
