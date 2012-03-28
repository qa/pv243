package cz.muni.fi.pv243.lesson03.action;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.lesson03.model.Bakery;

import java.io.Serializable;

@ConversationScoped
@Named
@Stateful
public class CurrentBakeryProducer implements Serializable {
	
	private Bakery bakery;
	
	@Inject
	EntityManager em;
	
	public void setBakery(Bakery bakery) {
		this.bakery = bakery;
	}
	
	public void setBakeryById(String bakeryId) {
		this.bakery = em.find(Bakery.class, Long.parseLong(bakeryId));
	}
	
	public String getBakeryById() {
	   if (this.bakery == null) {
	      return null;
	   }
	   
	   return this.bakery.getId().toString();
	}
	
	@Named
	@Produces
	@Current
	public Bakery getBakery() {
		return bakery;
	}
}
