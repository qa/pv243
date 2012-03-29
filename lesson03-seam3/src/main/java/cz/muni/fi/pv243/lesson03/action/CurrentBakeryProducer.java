package cz.muni.fi.pv243.lesson03.action;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.lesson03.model.Bakery;

import java.io.Serializable;

@ConversationScoped
@Named
@Stateful
public class CurrentBakeryProducer implements Serializable {
	
    @Inject
    CurrentBakeryUnwrapper bakeryUnwrapper;
	
	@Inject
	EntityManager em;
	
	public void setBakery(Bakery bakery) {
	   bakeryUnwrapper.setCurrentBakery(bakery);
	}
	
	public Bakery getBakery() {
	   return bakeryUnwrapper.getCurrentBakery();
	}
	
	public void setBakeryById(String bakeryId) {
		bakeryUnwrapper.setCurrentBakery(em.find(Bakery.class, Long.parseLong(bakeryId)));
	}
	
	public String getBakeryById() {
	   if (bakeryUnwrapper.getCurrentBakery() == null) {
	      return null;
	   }
	   
	   return bakeryUnwrapper.getCurrentBakery().getId().toString();
	}
}
