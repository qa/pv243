package cz.muni.fi.pv243.lesson02.students;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Holds the state of the student registration process.
 * @author Jozef Hartinger
 *
 */
@ConversationScoped
@Named
@Stateful
public class StudentRegistrationWizard {

    @Inject
    private Conversation conversation;

    @PersistenceContext
    private EntityManager manager;

    private final Student student = new Student();

    @Produces
    @Named
    public Student getStudent() {
        return student;
    }

    public void begin() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void cancel() {
        conversation.end();
    }

    public void register() {
        manager.persist(student);
        conversation.end();
    }
}
