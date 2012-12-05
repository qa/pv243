package org.jboss.ee6lab.cdi.wumpus.rooms;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.RoomName;

@RoomName("entry")
@SessionScoped
public class EntryRoom extends Room {
	@Inject
	public void inject(@RoomName("east") Room eastRoom, @RoomName("south") Room southRoom) {
		description = "You are in the entry room.";
		south = southRoom;
		east = eastRoom;
	}
}
