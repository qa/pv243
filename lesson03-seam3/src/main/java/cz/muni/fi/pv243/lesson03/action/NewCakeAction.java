package cz.muni.fi.pv243.lesson03.action;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.lesson03.model.Bakery;
import cz.muni.fi.pv243.lesson03.model.Cake;

@ConversationScoped
@Named
@Stateful
public class NewCakeAction implements Serializable
{
   @Inject
   private EntityManager em;
   
   @Inject
   @Current
   private Bakery bakery;
   
   private Cake newCake;
   
   
   
   @Produces
   @Named
   public Cake getNewCake() {
      return newCake;
   }
   
   @PostConstruct
   public void init() {
      newCake = new Cake();
   }
   
   public void createCake() {
      em.persist(newCake);
      bakery.getCakes().add(newCake);
      
      newCake = new Cake();
   }
}
