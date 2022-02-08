package se.iths.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SubjectService {

    @PersistenceContext
    private EntityManager em;


}
