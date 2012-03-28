package cz.muni.fi.pv243.lesson03.action;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import cz.muni.fi.pv243.lesson03.model.Cake;

@Model
public class CakeEditAction
{
   @Inject
   @Current
   private Cake cake;
   
   public void removeIngredient(String ingredient) {
      cake.getIngredients().remove(ingredient);
   }
   
   public void addIngredient(String ingredient) {
      cake.getIngredients().add(ingredient);
   }
}
