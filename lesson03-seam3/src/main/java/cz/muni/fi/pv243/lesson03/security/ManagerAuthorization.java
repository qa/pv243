package cz.muni.fi.pv243.lesson03.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

import cz.muni.fi.pv243.lesson03.action.Current;
import cz.muni.fi.pv243.lesson03.model.Bakery;
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
   
   @Secures
   @IsManagerOf
   public boolean isManagerOf(Identity identity, @Current Bakery bakery) {
      if (!identity.isLoggedIn()) {
         return false;
      }
            
      if (isAdmin(identity)) {
         return true;
      }
      
      // Only admins can edit non-assigned bakeries
      if (bakery.getManager() == null) {
         return false;
      }
      
      return identity.getUser().getId().equals(bakery.getManager().getId());
   }
}
