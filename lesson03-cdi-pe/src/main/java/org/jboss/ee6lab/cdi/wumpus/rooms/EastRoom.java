package org.jboss.ee6lab.cdi.wumpus.rooms;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.RoomName;

@RoomName("east")
@SessionScoped
public class EastRoom extends Room {
	
	@Inject
	public void inject(@RoomName("pit") Room pitRoom, @RoomName("entry") Room entryRoom) {
		description = "You are in the east room";
		south = pitRoom;
		west = entryRoom;
	}
}
