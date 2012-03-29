package cz.muni.fi.pv243.lesson03.action;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.jboss.solder.unwraps.Unwraps;

import cz.muni.fi.pv243.lesson03.model.Cake;

@ConversationScoped
public class CurrentCakeUnwrapper implements Serializable
{
   private Cake cake;
   
   public void setCurrentCake(Cake cake) {
      this.cake = cake;
   }
   
   public Cake getCurrentCake() {
      return cake;
   }
   
   @Unwraps
   @Current
   @Named("cake")
   public Cake produceCurrentCake() {
      return cake;
   }
}
