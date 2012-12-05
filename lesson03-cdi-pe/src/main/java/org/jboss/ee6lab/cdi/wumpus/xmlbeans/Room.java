package org.jboss.ee6lab.cdi.wumpus.xmlbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.ee6lab.cdi.wumpus.Player;

public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Room north;

	protected Room south;

	protected Room east;

	protected Room west;

	protected String description;
	
	protected String smell;

	public String getDescription() {
		return description;
	}
	
	public String getSmell() {
		return smell;
	}

	private List<Player> players = new ArrayList<Player>();

	public synchronized void addPlayer(Player player) {
		players.add(player);
	}

	public synchronized void removePlayer(Player player) {
		players.remove(player);
	}

	public synchronized void clear() {
		players = new ArrayList<Player>();
	}

	public synchronized List<Player> getPlayers() {
		return new ArrayList<Player>(players);
	}

	public Room getNorth() {
		return north;
	}

	public Room getSouth() {
		return south;
	}

	public Room getEast() {
		return east;
	}

	public Room getWest() {
		return west;
	}
}
