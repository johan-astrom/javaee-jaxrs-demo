package se.iths.service;

import se.iths.dto.TeacherGetDto;
import se.iths.entity.Teacher;
import se.iths.exception.EntityNotFoundServiceException;
import se.iths.mapper.TeacherMapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TeacherService {

    @PersistenceContext
    private EntityManager em;

    public List<TeacherGetDto> getAllTeachers() {
        List<TeacherGetDto> teacherGetDtos = new ArrayList<>();
        var teachers = em.createNamedQuery("Teacher.GetAll", Teacher.class).getResultList();
        for (Teacher teacher : teachers) {
            teacherGetDtos.add(TeacherMapper.teacherToTeacherGetDto(teacher));
        }
        return teacherGetDtos;
    }

    public TeacherGetDto getTeacherById(Long id) {
        var teacher = em.find(Teacher.class, id);
        return TeacherMapper.teacherToTeacherGetDto(teacher);
    }

    public TeacherGetDto getTeacherByLastName(String lastName) {
        var teacher = em.createQuery("select t from Teacher t where t.lastName = :lastName", Teacher.class)
                .setParameter("lastName", lastName)
                .getSingleResult();
        return TeacherMapper.teacherToTeacherGetDto(teacher);
    }

    public Teacher createTeacher(Teacher teacher) {
        em.persist(teacher);
        return teacher;
    }

    public void deleteTeacher(Long id) throws EntityNotFoundServiceException {
        Teacher teacher = em.find(Teacher.class, id);
        if (teacher == null) throw new EntityNotFoundServiceException("There is no teacher with the specified id.");
        else em.remove(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) throws EntityNotFoundServiceException {
        Teacher originalTeacher = em.find(Teacher.class, id);
        if (originalTeacher == null)
            throw new EntityNotFoundServiceException("There is no teacher with the specified id.");
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
