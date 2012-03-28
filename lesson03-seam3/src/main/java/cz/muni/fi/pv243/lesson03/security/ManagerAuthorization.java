package cz.muni.fi.pv243.lesson03.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

import cz.muni.fi.pv243.lesson03.model.Manager;

public class ManagerAuthorization
{
   @Secures
   @Admin
   public boolean isAdmin(Identity identity) {
      if (!identity.isLoggedIn()) {
         return false;
      }
      return ((Manager)identity.getUser()).isAdmin();
   }
}
