package org.jboss.ee6lab.cdi.wumpus;

import java.util.List;

import javax.enterprise.event.Observes;

import org.jboss.ee6lab.cdi.wumpus.observerorder.ObserverOrder;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;

// TODO: order the events with our observerorder extension
public class RoomEventsNarrator {
	
	/*
	 * Implements the logic of killing players by an arrow
	 */
	public void shootAtRoomObserver(@Observes PlayerShootAtRoomEvent event, GameMessage gameMessage, @Current Player currentPlayer) {
		
		Room room = event.getRoom();
		List<Player> players = room.getPlayers();
		
		boolean shotSomebody = false;
		if (!players.isEmpty()) {

			for (Player player : players) {
				shotSomebody = true;
				player.setAlive(false);
				player.setDeathMessage("You have been shot while you were idly standing!");
			}
		
			room.clear();
		}

		if (shotSomebody) {
			gameMessage.add("Your arrow hit a target!");
		}
	}
	
	public void roomDescriptionObserver(@Observes @ObserverOrder(0) PlayerEnteredRoomEvent event, GameMessage gameMessage, @Current Room currentRoom, @Current Player currentPlayer) {
		gameMessage.add(currentRoom.getDescription());
	}
	
	public void roomSmellObserver(@Observes @ObserverOrder(2) PlayerEnteredRoomEvent event, GameMessage gameMessage, @Current Room currentRoom) {
		if (currentRoom.getNorth() != null && currentRoom.getNorth().getSmell() != null) {
			gameMessage.add(currentRoom.getNorth().getSmell());
		}
		if (currentRoom.getWest() != null && currentRoom.getWest().getSmell() != null) {
			gameMessage.add(currentRoom.getWest().getSmell());
		}
		if (currentRoom.getEast() != null && currentRoom.getEast().getSmell() != null) {
			gameMessage.add(currentRoom.getEast().getSmell());
		}
		if (currentRoom.getSouth() != null && currentRoom.getSouth().getSmell() != null) {
			gameMessage.add(currentRoom.getSouth().getSmell());
		}
	}
	
	public void playerSmellObserver(@Observes @ObserverOrder(3) PlayerEnteredRoomEvent event, GameMessage gameMessage, @Current Room currentRoom) {
		boolean smellsPlayer = false;
		
		if (currentRoom.getNorth() != null) {
			if (currentRoom.getNorth().getPlayers().size() > 0) {
				smellsPlayer = true;
			}
		}
		if (currentRoom.getSouth() != null) {
			if (currentRoom.getSouth().getPlayers().size() > 0) {
				smellsPlayer = true;
			}
		}
		if (currentRoom.getEast() != null) {
			if (currentRoom.getEast().getPlayers().size() > 0) {
				smellsPlayer = true;
			}
		}
		if (currentRoom.getWest() != null) {
			if (currentRoom.getWest().getPlayers().size() > 0) {
				smellsPlayer = true;
			}
		}
		
		if (smellsPlayer) {
			gameMessage.add("You smell another player nearby!");
		}
	}
	
	public void playerSightObserver(@Observes @ObserverOrder(1) PlayerEnteredRoomEvent event, GameMessage gameMessage, @Current Room currentRoom, @Current Player currentPlayer) {
		for (Player player : currentRoom.getPlayers()) {
			if (player != currentPlayer) {
				gameMessage.add(player.getName() + " is in the room.");
			}
		}
	}
}
