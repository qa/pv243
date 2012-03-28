package cz.muni.fi.pv243.lesson03.security;

import javax.enterprise.event.Observes;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.events.LoggedInEvent;
import org.jboss.solder.logging.Logger;

import cz.muni.fi.pv243.lesson03.action.BakeryEditedEvent;
import cz.muni.fi.pv243.lesson03.action.Current;
import cz.muni.fi.pv243.lesson03.model.Bakery;

public class ManagerLogging {
	
	public void managerLoggedIn(@Observes LoggedInEvent event, Logger log) {
		log.info("Manager " + event.getUser().getId() + " logged in.");
    }
	
	public void bakeryEdited(@Observes BakeryEditedEvent event, Logger log, Identity identity, @Current Bakery bakery) {
		log.info("Bakery " + bakery.getName() + " edited by " + identity.getUser().getId() );
	}
}
