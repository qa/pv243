package cz.muni.fi.pv243.lesson03.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import cz.muni.fi.pv243.lesson03.model.Manager;

@Stateless
@Named
public class ManagerList
{
   @Inject
   private EntityManager em;
   
   @Produces
   @Model
   public List<Manager> getManagers() {
       return em.createQuery("select m from Manager m order by m.username", Manager.class)
               .getResultList();
   }
   
   public String view() {
      return "managers";
   }
}
