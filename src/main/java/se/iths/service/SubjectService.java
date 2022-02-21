package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.SubjectDto;
import se.iths.entity.Teacher;
import se.iths.mapper.SubjectMapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SubjectService {

    @PersistenceContext
    private EntityManager em;

    public Subject createSubject(Subject subject, Long teacherId){
        Teacher teacher = em.find(Teacher.class, teacherId);
        subject.setTeacher(teacher);
        teacher.getSubjects().add(subject);

        em.persist(subject);
        em.persist(teacher);
        
        return subject;
    }

    public List<SubjectDto> getAllSubjects(){
        List<SubjectDto> subjectDtos = new ArrayList<>();
        var subjects = em.createNamedQuery("Subject.GetAll", Subject.class).getResultList();
        for (Subject subject : subjects){
            subjectDtos.add(SubjectMapper.subjectToSubjectDto(subject));
        }
        return subjectDtos;
    }

    public SubjectDto getSubjectById(Long id){
        Subject subject = em.find(Subject.class, id);
        return SubjectMapper.subjectToSubjectDto(subject);
    }
}
