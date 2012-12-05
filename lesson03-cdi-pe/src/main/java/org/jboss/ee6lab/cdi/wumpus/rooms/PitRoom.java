package org.jboss.ee6lab.cdi.wumpus.rooms;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.RoomName;

@RoomName("pit")
@SessionScoped
public class PitRoom extends Room {
	
	@Inject
	public void inject() {
		description = "You fell into a bottomless pit!";
		smell = "You feel a breeze.";
	}
}
