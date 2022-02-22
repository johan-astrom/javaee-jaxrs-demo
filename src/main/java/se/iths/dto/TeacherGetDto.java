package se.iths.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class TeacherGetDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final List<TeacherSubjectDto> subjects;

    public TeacherGetDto(Long id, String firstName, String lastName, String email, String phoneNumber, List<TeacherSubjectDto> subjects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<TeacherSubjectDto> getSubjects() {
        return subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherGetDto entity = (TeacherGetDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.subjects, entity.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, subjects);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "email = " + email + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "subjects = " + subjects + ")";
    }
}
