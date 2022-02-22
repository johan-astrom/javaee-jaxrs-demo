package se.iths.entity;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Subject.GetAll",
                query = "select s from Subject s")})
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
