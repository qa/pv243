package cz.muni.fi.pv243.lesson03.security;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

import cz.muni.fi.pv243.lesson03.model.Manager;

public class ManagerAuthenticator extends BaseAuthenticator
{
   @Inject
   Credentials credentials;

   @Inject
   EntityManager em;
   
   @Override
   public void authenticate()
   {
      try {
         Manager manager = (Manager)em.createQuery("select m from Manager m where m.username = :username and m.password = :password")
         .setParameter("username", credentials.getUsername())
         .setParameter("password", ((PasswordCredential)credentials.getCredential()).getValue()).getSingleResult();
         
         setStatus(AuthenticationStatus.SUCCESS);
         setUser(manager);
      }
      catch(NoResultException x) {
         setStatus(AuthenticationStatus.FAILURE);
      }
   }
}
