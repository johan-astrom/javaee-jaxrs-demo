package se.iths.service;

import se.iths.entity.Teacher;
import se.iths.exception.EntityNotFoundServiceException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TeacherService {

    @PersistenceContext
    private EntityManager em;

    public List<Teacher> getAllTeachers() {
        return em.createNamedQuery("Teacher.GetAll", Teacher.class).getResultList();
    }

    public Teacher getTeacherById(Long id) {
        return em.find(Teacher.class, id);
    }

    public Teacher getTeacherByLastName(String lastName) {
        return em.createQuery("select t from Teacher t where t.lastName = :lastName", Teacher.class)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }

    public Teacher createTeacher(Teacher teacher) {
        em.persist(teacher);
        return teacher;
    }

    public void deleteTeacher(Long id) throws EntityNotFoundServiceException {
        Teacher teacher = getTeacherById(id);
        if (teacher == null) throw new EntityNotFoundServiceException("There is no teacher with the specified id.");
        else em.remove(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) throws EntityNotFoundServiceException {
        Teacher originalTeacher = getTeacherById(id);
        if (originalTeacher == null) throw new EntityNotFoundServiceException("There is no teacher with the specified id.");
        else {
            setTeacherDetails(teacher, originalTeacher);
            return originalTeacher;
        }
    }

    private void setTeacherDetails(Teacher teacher, Teacher originalTeacher) {
        originalTeacher.setFirstName(teacher.getFirstName());
        originalTeacher.setLastName(teacher.getLastName());
        originalTeacher.setEmail(teacher.getEmail());
        originalTeacher.setPhoneNumber(teacher.getPhoneNumber());
    }


}
