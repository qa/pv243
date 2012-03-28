package cz.muni.fi.pv243.lesson03.exceptions;

import javax.faces.context.FacesContext;
import javax.persistence.OptimisticLockException;

import org.jboss.seam.international.status.Messages;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

@HandlesExceptions
public class ExceptionHandlers
{
   void handleOptimisticLock(@Handles CaughtException<OptimisticLockException> caught, Messages messages, FacesContext facesContext)
   {
	  messages.error("Data has been modified in the meantime, your data was NOT saved! Sorry...");
      facesContext.getApplication().getNavigationHandler()
            .handleNavigation(facesContext, null, "bakerylist");
   }
}
