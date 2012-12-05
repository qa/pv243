package org.jboss.ee6lab.cdi.wumpus;

import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.ee6lab.cdi.wumpus.observerorder.ObserverOrder;
import org.jboss.ee6lab.cdi.wumpus.scope.GameScoped;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;

@GameScoped
public class Wumpus {
	@Inject
	@Random
	private Room room;

	public void onShoot(@Observes PlayerShootAtRoomEvent event,
			GameMessage gameMessage, @Current Room currentRoom,
			@Current Player currentPlayer, @Current List<Player> players) {
		if (event.getRoom() == room) {
			// Technically, they are not dead, they're just resting...
			currentPlayer.setAlive(false);
			currentPlayer
					.setDeathMessage("You have killed the wumpus! You won!");

			for (Player p : players) {
				if (p != currentPlayer) {
					p.setAlive(false);
					p.setDeathMessage("Player " + currentPlayer.getName()
							+ " killed the wumpus and won the game!");
				}
			}
		}
	}

	private boolean isWumpusNearby(Room otherRoom) {
		if (otherRoom == null) {
			return false;
		}

		if (otherRoom == room || otherRoom.getNorth() == room
				|| otherRoom.getSouth() == room || otherRoom.getEast() == room
				|| otherRoom.getWest() == room) {
			return true;
		}

		return false;
	}

	public void onMove(
			@Observes PlayerEnteredRoomEvent event,
			GameMessage gameMessage, @Current Room currentRoom,
			@Current Player currentPlayer) {

		// System.out.println("Wumpus is in " + room.getDescription());

		if (currentRoom == room) {
			currentPlayer.setAlive(false);
			currentPlayer.setDeathMessage("You have been eaten by a wumpus!");

			return;
		}

		boolean smellWumpus = false;

		// Wumpus smells really bad, you can smell it from the distance of two
		// rooms!
		if (isWumpusNearby(currentRoom.getNorth())) {
			smellWumpus = true;
		}
		if (isWumpusNearby(currentRoom.getEast())) {
			smellWumpus = true;
		}
		if (isWumpusNearby(currentRoom.getWest())) {
			smellWumpus = true;
		}
		if (isWumpusNearby(currentRoom.getSouth())) {
			smellWumpus = true;
		}

		if (smellWumpus) {
			gameMessage.add("You smell a wumpus!");
		}
	}
}