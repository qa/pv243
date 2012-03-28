package cz.muni.fi.pv243.lesson03.action;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.faces.context.conversation.End;

@Named
@Stateless
public class BakeryAction
{
   @Inject
   private EntityManager em;
   
   @Begin
   public void edit()
   {
   }

   @End
   public void save()
   {
      em.flush();
   }
   
   @End
   public void cancel()
   {
      
   }
}
