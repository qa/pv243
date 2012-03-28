package cz.muni.fi.pv243.lesson03.action;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.faces.context.conversation.Begin;

import cz.muni.fi.pv243.lesson03.model.Bakery;

@Model
@Stateful
public class NewBakeryAction
{
   @Inject
   EntityManager em;
   
   private Bakery newBakery; 
   
   @Produces
   @Named
   public Bakery getNewBakery() {
      return newBakery;
   }
   
   @Inject
   CurrentBakeryProducer currentBakeryProducer;
   
   @Begin
   public void createBakery() {
      em.persist(newBakery);
      currentBakeryProducer.setBakery(newBakery);
   }
   
   @PostConstruct
   public void init() {
      newBakery = new Bakery();
   }
}
