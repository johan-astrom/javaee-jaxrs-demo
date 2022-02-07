package se.iths.service;


import se.iths.entity.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    public List<Student> getAllStudents(){
        return em.createNamedQuery("Student.GetAll", Student.class).getResultList();
    }

    public Student getStudentById(Long id){
        return em.find(Student.class, id);
    }

    public Student getStudentByLastName(String lastName){
        return em.createQuery("select s from Student s where s.lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }

    public Student createStudent(Student student){
        em.persist(student);
        return student;
    }

    public Student updateStudent(Long id, Student student){
        Student originalStudent = getStudentById(id);
        originalStudent.setFirstName(student.getFirstName());
        originalStudent.setLastName(student.getLastName());
        originalStudent.setEmail(student.getEmail());
        originalStudent.setPhoneNumber(student.getPhoneNumber());

        return originalStudent;
    }

    public void deleteStudent(Long id){
        Student student = getStudentById(id);
        em.remove(student);
    }




}
