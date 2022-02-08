package se.iths.service;

import se.iths.entity.Subject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SubjectService {

    @PersistenceContext
    private EntityManager em;

    public Subject createSubject(Subject subject){
        em.persist(subject);
        return subject;
    }

    public Subject getSubjectById(Long id){
        return em.find(Subject.class, id);
    }
}
