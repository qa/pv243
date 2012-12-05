package org.jboss.ee6lab.cdi.wumpus;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.ee6lab.cdi.wumpus.scope.CurrentGameId;
import org.jboss.ee6lab.cdi.wumpus.scope.GamesManager;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.Room;
import org.jboss.ee6lab.cdi.wumpus.xmlbeans.RoomName;

@SessionScoped
public class CurrentPlayerManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int currentGameId = 0;
	
	@Inject
	private Player currentPlayer;
	
	@Inject
	@RoomName("entry")
	// TODO: Inject a random room instead
	// @Random
	private Instance<Room> initialRoom;
	
	@Inject
	Event<PlayerLogoffEvent> playerLogoffEvent;
	
	@Inject
	Event<PlayerLoginEvent> playerLoginEvent;
	
	@Inject
	Event<PlayerJoinedGameEvent> playerJoinedGameEvent;
	
	@Inject
	Event<PlayerLeftGameEvent> playerLeftGameEvent;
	
	@Inject
	Event<PlayerEnteredRoomEvent> playerEnteredRoomEvent;
	
	@Inject
	Event<PlayerShootAtRoomEvent> playerShootAtRoomEvent;
	
	@Inject
	GamesManager gamesManager;
	
	private Room currentRoom;
	
	@Produces
	@CurrentGameId
	public Integer getCurrentGameId() {
		return currentGameId;
	}
	
	@Produces
	@Current
	@Named
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	@Produces
	@Current
	public List<Player> getCurrentPlayers() {
		return gamesManager.getGame(currentGameId).getPlayers();
	}
	
	@Inject
	GameMessage gameMessage;
	
	public void joinGame(int gameId) {
		currentGameId = gameId;
		currentPlayer.setAlive(true);
		moveTo(initialRoom.get());
		playerJoinedGameEvent.fire(new PlayerJoinedGameEvent());
	}
	
	public void leaveGame() {
		
		moveTo(null);
		
		playerLeftGameEvent.fire(new PlayerLeftGameEvent());
		
		currentGameId = 0;
		currentRoom = null;
	}
	
	public void moveTo(Room room) {
		
		if (currentRoom != null) {
			currentRoom.removePlayer(currentPlayer);
		}
		
		if (room != null) {
			currentRoom = room;
			room.addPlayer(currentPlayer);
	
			playerEnteredRoomEvent.fire(new PlayerEnteredRoomEvent());
		}
	}
	
	public void shootAt(Room room) {
		playerShootAtRoomEvent.fire(new PlayerShootAtRoomEvent(room));
	}

	@Produces
	@Current
	@Named
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void login() {
		playerLoginEvent.fire(new PlayerLoginEvent());
		currentPlayer.setLoggedIn(true);
	}
	
	@PreDestroy
	public void preDestroy() {
		
		moveTo(null);
		
		playerLeftGameEvent.fire(new PlayerLeftGameEvent());
		playerLogoffEvent.fire(new PlayerLogoffEvent());
		System.out.println("XXX predestroy on " + currentPlayer.getName());
	}
}
