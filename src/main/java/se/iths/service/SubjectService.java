package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SubjectService {

    @PersistenceContext
    private EntityManager em;

    public Subject createSubject(Subject subject, Long teacherId){
        Teacher teacher = em.find(Teacher.class, teacherId);
        subject.setTeacher(teacher);
        em.persist(subject);
        return subject;
    }

    public List<Subject> getAllSubjects(){
        return em.createNamedQuery("Subject.GetAll", Subject.class).getResultList();
    }

    public Subject getSubjectById(Long id){
        return em.find(Subject.class, id);
    }
}
