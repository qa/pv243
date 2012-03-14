package cz.muni.fi.pv243.lesson02.students;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Loads a list of {@link Student}s and makes it available under the "allStudentsSortedByName" EL name.
 *
 * @author Jozef Hartinger
 *
 */
@Stateless
public class StudentList {

    @PersistenceContext
    private EntityManager manager;

    @Produces
    @Model
    public List<Student> getAllStudentsSortedByName() {
        return manager.createQuery("select student from Student student order by student.surname", Student.class)
                .getResultList();
    }
}
