package org.jboss.ee6lab.cdi.wumpus.scope;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;


public class GameScopeExtension implements Extension {
	public void afterBeanDiscovery(@Observes AfterBeanDiscovery event, BeanManager manager) {
		// TODO, register GameContext
    }
}
