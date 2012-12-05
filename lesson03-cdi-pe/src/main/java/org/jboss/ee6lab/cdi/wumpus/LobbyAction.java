package org.jboss.ee6lab.cdi.wumpus;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.jboss.ee6lab.cdi.wumpus.scope.GamesManager;

@Model
public class LobbyAction {
	@Inject
	CurrentPlayerManager currentPlayerManager;
	
	@Inject
	GamesManager gamesManager;
	
	public String login() {
		currentPlayerManager.login();
		return "lobby?faces-redirect=true";
	}
	
	public String createNewGame() {
		int gameId = gamesManager.createNewGame().getId();
		currentPlayerManager.joinGame(gameId);
		
		return "room?faces-redirect=true";
	}
	
	public String joinGame(Integer gid) {
		currentPlayerManager.joinGame(gid);

		return "room?faces-redirect=true";
	}
	
	public String leaveGame() {
		currentPlayerManager.leaveGame();
		
		return "lobby?faces-redirect=true";
	}
}
