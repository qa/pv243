package org.jboss.ee6lab.cdi.wumpus.scope;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.apache.deltaspike.core.util.context.AbstractContext;
import org.apache.deltaspike.core.util.context.ContextualInstanceInfo;
import org.apache.deltaspike.core.util.context.ContextualStorage;

public class GameContext extends AbstractContext {
	
	private static Map<Integer, ContextualStorage> gameStores = new HashMap<Integer, ContextualStorage> ();
	
	private BeanManager beanManager;
	
	public GameContext(BeanManager beanManager) {
		super(beanManager);
		this.beanManager = beanManager;
	}

	private int getCurrentGameId() {
		// TODO: retrieve the '@CurrentGameId Integer' contextual instance  
		Set<Bean<?>> beans = beanManager.getBeans(Integer.class, new CurrentGameId.CurrentGameIdLiteral());
		if (beans.size() == 1) {
			Bean<Integer> bean = (Bean<Integer>)beans.iterator().next();

			CreationalContext<Integer> ctx = beanManager.createCreationalContext(bean);
			Integer gid = (Integer)beanManager.getReference(bean, Integer.class, ctx);

			bean.destroy(gid, ctx);

			return gid;
		}
		else {
			throw new IllegalStateException("no unambiguous CurrentGameId bean found");
		}
	}
	
	private ContextualStorage createGameStorage(int gid) {
		ContextualStorage storage = new ContextualStorage(beanManager, true, false);
		gameStores.put(gid, storage);
		return storage;
	}
	
	public void createGame(int gid) {
		if (!gameStores.containsKey(gid)) {
			createGameStorage(gid);
		}
	}
	
	public void deleteGame(int gid) {
		if (gameStores.containsKey(gid)) {
			ContextualStorage storage = gameStores.get(gid);
			
			Map<Object, ContextualInstanceInfo<?>> contextMap = storage.getStorage();
	        for (Map.Entry<Object, ContextualInstanceInfo<?>> entry : contextMap.entrySet())
	        {
	            Contextual bean = storage.getBean(entry.getKey());

	            ContextualInstanceInfo<?> contextualInstanceInfo = entry.getValue();
	            bean.destroy(contextualInstanceInfo.getContextualInstance(), contextualInstanceInfo.getCreationalContext());
	        }
			
			gameStores.remove(gid);
		}
	}
	
	public Collection<Integer> getGameIds() {
		return gameStores.keySet();
	}

	@Override
	protected ContextualStorage getContextualStorage(boolean createIfNotExist) {
		int gid = getCurrentGameId();
		
		if (gameStores.containsKey(gid)) {
			return gameStores.get(gid);
		}
		
		if (createIfNotExist) {
			throw new IllegalStateException("Accessing non-existing game context: " + gid + ".");
		}
		
		return null;
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return GameScoped.class;
	}

	@Override
	public boolean isActive() {
		return true;
	}
}
