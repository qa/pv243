package cz.muni.fi.pv243.lesson03.action;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.international.status.Messages;

import cz.muni.fi.pv243.lesson03.model.Manager;

@Model
@Stateful
public class RegisterAction {
	
	@PersistenceContext
	EntityManager em;
	
	private Manager manager;
	
	@PostConstruct
	public void initialize() {
		manager = new Manager();
	}
	
	@Inject
	Messages messages;
	
	public void create() {
		em.persist(manager);
		messages.info("New manager {0} created", manager.getId());
	}
	
	@Produces
	@Named
	public Manager getNewManager() {
		return manager;
	}
}
