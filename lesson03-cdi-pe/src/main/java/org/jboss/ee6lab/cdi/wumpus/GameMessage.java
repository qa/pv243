package org.jboss.ee6lab.cdi.wumpus;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class GameMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	StringBuilder builder = new StringBuilder();
	
	@Inject
	@Current
	Player player;
	
	public void add(String message) {
		if (message != null) {
			builder.append(message);
	        builder.append('\n');
	    }
	}

	public String getMessage() {
		
		String ret = builder.toString();
		builder = new StringBuilder();
		
		if (!player.isAlive()) {
			return player.getDeathMessage();
		}

		if (ret.isEmpty()) {
			return "Nothing happens...";
		}
		
		return ret;
	}
}
