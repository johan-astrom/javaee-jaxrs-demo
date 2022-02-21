package se.iths.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SubjectDto implements Serializable {
    private final Long id;
    private final String name;
    private final List<StudentDto> students;
    private final TeacherDto teacher;

    public SubjectDto(Long id, String name, List<StudentDto> students, TeacherDto teacher) {
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

    public List<StudentDto> getStudents() {
        return students;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto entity = (SubjectDto) o;
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
