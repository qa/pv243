package cz.muni.fi.pv243.lesson03.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DemoInitializer
{
   @PersistenceContext
   private EntityManager em;
   
   @PostConstruct
   public void initialize()
   {      
      Manager admin = new Manager("admin", "admin", true);
      em.persist(admin);
      
      Manager user1 = new Manager("manager1", "manager", false);
      em.persist(user1);
      
      Manager user2 = new Manager("manager2", "manager", false);
      em.persist(user2);
      
      List<Cake> cakes = new LinkedList<Cake>();
      Cake cake = new Cake("Unconceivably Sweet Pie", BigDecimal.valueOf(19990, 2), "white flavor", "strawberry filling",
            "lemon icing");
      em.persist(cake);
      cakes.add(cake);

      cake = new Cake("Humperdinck's Favourite", BigDecimal.valueOf(29990, 2), "chocolate");
      em.persist(cake);
      cakes.add(cake);

      cake = new Cake("Dread Pirate Pudding", BigDecimal.valueOf(29990, 2), "vanilla flavor");
      em.persist(cake);
      cakes.add(cake);

      Bakery store = new Bakery("Buttercup Bakery", "7 W.Well St., Neverland", user1);
      store.setCakes(cakes);
      em.persist(store);

      cakes = new LinkedList<Cake>();
      cake = new Cake("Neurotoxin Pie", BigDecimal.valueOf(59990, 2), "love", "chocolate");
      em.persist(cake);
      cakes.add(cake);

      cake = new Cake("Cake Lie", BigDecimal.valueOf(0, 2), "lots of testing");
      em.persist(cake);
      cakes.add(cake);

      cake = new Cake("Companion Pie", BigDecimal.valueOf(19990, 2), "no artificial additives");
      em.persist(cake);
      cakes.add(cake);

      store = new Bakery("Aperture Bakery", "42 Meme Lane, Xanadu", user2);
      store.setCakes(cakes);
      em.persist(store);
   }
}