package org.jboss.ee6lab.cdi.wumpus;

import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;

public class PlayerShootAtRoomEvent {
	private Room room;
	
	public PlayerShootAtRoomEvent(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
}
