package org.jboss.ee6lab.cdi.wumpus.lobby;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.jboss.ee6lab.cdi.wumpus.Current;
import org.jboss.ee6lab.cdi.wumpus.Player;
import org.jboss.ee6lab.cdi.wumpus.PlayerLoginEvent;
import org.jboss.ee6lab.cdi.wumpus.PlayerLogoffEvent;

@ApplicationScoped
public class Lobby {
	private List<Player> players = new LinkedList<Player> ();
	
	public void onPlayerLogin(@Observes PlayerLoginEvent event, @Current Player player) throws PlayerExistsException {
		for (Player p : players) {
			if (p.getName().equals(player.getName())) {
				throw new PlayerExistsException();
			}
		}
		
		players.add(player);
	}
	
	public void onPlayerLogoff(@Observes PlayerLogoffEvent event, @Current Player player) {
		Iterator<Player> i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getName().equals(player.getName())) {
				i.remove();
			}
		}
	}
	
	@Produces
	@Named
	public List<Player> getPlayers() {
		return players;
	}
}
