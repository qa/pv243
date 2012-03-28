package cz.muni.fi.pv243.lesson03.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
public class Cake implements Serializable
{
   private Long id;

   public Cake()
   {
   }

   public Cake(String name, BigDecimal price, String... ingredients)
   {
      this.name = name;
      this.price = price;
      this.ingredients = new ArrayList<String>(ingredients.length);
      Collections.addAll(this.ingredients, ingredients);
   }

   @Id
   @GeneratedValue
   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   @Version
   public Long getVersion()
   {
      return version;
   }

   public void setVersion(Long version)
   {
      this.version = version;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public BigDecimal getPrice()
   {
      return price;
   }

   public void setPrice(BigDecimal price)
   {
      this.price = price;
   }

   @ElementCollection
   public List<String> getIngredients()
   {
      if (ingredients == null) {
         ingredients = new LinkedList<String>();
      }
      return ingredients;
   }

   public void setIngredients(List<String> ingredients)
   {
      this.ingredients = ingredients;
   }

   @Transient
   public String getIngredientString()
   {
      StringBuilder sb = new StringBuilder();
      for (String ingredient : this.ingredients)
      {
         if (sb.length() > 0)
         {
            sb.append(", ");
         }
         sb.append(ingredient);
      }
      return sb.toString();
   }

   private Long version;

   private String name;

   private BigDecimal price;

   private List<String> ingredients;
}
