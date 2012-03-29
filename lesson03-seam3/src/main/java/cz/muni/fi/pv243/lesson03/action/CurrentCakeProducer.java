package cz.muni.fi.pv243.lesson03.action;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.lesson03.model.Cake;

import java.io.Serializable;

@ConversationScoped
@Named
@Stateful
public class CurrentCakeProducer implements Serializable {
	
	@Inject
	public CurrentCakeUnwrapper cakeUnwrapper;
	
	@Inject
	EntityManager em;
	
	public void setCake(Cake cake) {
	   cakeUnwrapper.setCurrentCake(cake);
	}
	
	public Cake getCake() {
	   return cakeUnwrapper.getCurrentCake();
	}
}
