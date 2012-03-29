package cz.muni.fi.pv243.lesson03.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.events.LoggedInEvent;
import org.jboss.solder.logging.Logger;

import cz.muni.fi.pv243.lesson03.action.BakeryEditedEvent;
import cz.muni.fi.pv243.lesson03.action.Current;
import cz.muni.fi.pv243.lesson03.model.Bakery;

@ApplicationScoped
public class ManagerLogging {
   
   
   @Inject
   @Current
   Bakery bakery;
	
	public void managerLoggedIn(@Observes LoggedInEvent event, Logger log) {
		log.info("Manager " + event.getUser().getId() + " logged in.");
    }
	
	public void bakeryEdited(@Observes BakeryEditedEvent event, Logger log, Identity identity) {
	 	log.info("Bakery " + bakery.getName() + " edited by " + identity.getUser().getId() );
	}
}
