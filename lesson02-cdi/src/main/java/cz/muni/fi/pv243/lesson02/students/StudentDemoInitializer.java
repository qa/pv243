package cz.muni.fi.pv243.lesson02.students;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Fills database with demo records.
 *
 * @author Jozef Hartinger
 *
 */
@Singleton
@Startup
public class StudentDemoInitializer {

	@PersistenceContext
	private EntityManager manager;

	@PostConstruct
	public void initialize() {
		manager.persist(new Student("Jozef", "Hartinger",
				"jharting@redhat.com", Country.SLOVAKIA, "Martin"));
		manager.persist(new Student("Marek", "Schmidt", "maschmid@redhat.com",
				Country.CZECH_REPUBLIC, "Karvina"));
	}
}
