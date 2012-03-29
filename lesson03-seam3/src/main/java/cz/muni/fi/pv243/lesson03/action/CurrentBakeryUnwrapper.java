package cz.muni.fi.pv243.lesson03.action;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.jboss.solder.unwraps.Unwraps;

import cz.muni.fi.pv243.lesson03.model.Bakery;

@ConversationScoped
public class CurrentBakeryUnwrapper implements Serializable
{
   private Bakery bakery;
   
   public void setCurrentBakery(Bakery bakery) {
      this.bakery = bakery;
   }
   
   public Bakery getCurrentBakery() {
      return bakery;
   }
   
   @Unwraps
   @Current
   @Named("bakery")
   public Bakery produceCurrentBakery() {
      return bakery;
   }
}
