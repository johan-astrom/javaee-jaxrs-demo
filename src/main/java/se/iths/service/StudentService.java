package se.iths.service;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exception.EntityNotFoundServiceException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    public List<Student> getAllStudents() {
        return em.createNamedQuery("Student.GetAll", Student.class).getResultList();
    }

    public Student getStudentById(Long id) {
        return em.find(Student.class, id);
    }

    public Student getStudentByLastName(String lastName) {
        return em.createQuery("select s from Student s where s.lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }

    public Student createStudent(Student student) {
        em.persist(student);
        return student;
    }

    public void deleteStudent(Long id) throws EntityNotFoundServiceException {
        Student student = getStudentById(id);
        if (student == null) throw new EntityNotFoundServiceException("There is no student with the specified id.");
        else em.remove(student);
    }

    public Student updateStudent(Long id, Student student) throws EntityNotFoundServiceException {
        Student originalStudent = getStudentById(id);
        if (originalStudent == null) throw new EntityNotFoundServiceException("There is no student with the specified id.");
        else {
            setStudentDetails(student, originalStudent);
            return originalStudent;
        }
    }

    private void setStudentDetails(Student student, Student originalStudent) {
        originalStudent.setFirstName(student.getFirstName());
        originalStudent.setLastName(student.getLastName());
        originalStudent.setEmail(student.getEmail());
        originalStudent.setPhoneNumber(student.getPhoneNumber());
    }

    public void addStudentSubject(Long studentId, Long subjectId){
        Student student = em.find(Student.class, studentId);
        Subject subject = em.find(Subject.class, subjectId);

        student.getSubjects().add(subject);
        subject.getStudents().add(student);

        em.persist(student);
        em.persist(subject);

    }


}
