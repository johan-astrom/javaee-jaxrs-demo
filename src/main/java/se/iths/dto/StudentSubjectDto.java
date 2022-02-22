package se.iths.dto;

import java.io.Serializable;
import java.util.Objects;

public class StudentSubjectDto implements Serializable {
    private final Long id;
    private final String name;
    private final SubjectTeacherDto teacher;

    public StudentSubjectDto(Long id, String name, SubjectTeacherDto teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SubjectTeacherDto getTeacher() {
        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSubjectDto entity = (StudentSubjectDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.teacher, entity.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teacher);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "teacher = " + teacher + ")";
    }
}
