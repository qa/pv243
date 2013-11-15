/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.jboss.datagrid.carmart.session;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import com.jboss.datagrid.carmart.session.StatisticsProvider;

@Named("stats")
@ApplicationScoped //use application scope so that we can get overall statistics
@Listener
public class LocalStatisticsProvider implements StatisticsProvider {

    @Inject
    private CacheContainerProvider provider;

    AtomicInteger modifications = new AtomicInteger(0);
    AtomicInteger creations = new AtomicInteger(0);
    AtomicInteger removals = new AtomicInteger(0);

    @PostConstruct
    public void getStatsObject() {
        ((DefaultCacheManager) provider.getCacheContainer()).getCache(CarManager.CAR_CACHE_NAME).addListener(this);
    }
    
    @CacheEntryCreated
    public void print(CacheEntryCreatedEvent event) {
        if (!event.isPre()) {
    	    creations.incrementAndGet();
    	}
    }
    
    @CacheEntryModified
    public void print(CacheEntryModifiedEvent event) {
    	if (!event.isPre()) {
    	    modifications.incrementAndGet();
    	}
    }
    
    @CacheEntryRemoved
    public void print(CacheEntryRemovedEvent event) {
    	if (!event.isPre()) {
    	    removals.incrementAndGet();
    	}
    }
    
    public String getModifications() {
        return String.valueOf(modifications);
    }

    public String getCreations() {
        return String.valueOf(creations);
    }

    public String getRemovals() {
        return String.valueOf(removals);
    }
}
