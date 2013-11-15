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

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.stats.Stats;
import com.jboss.datagrid.carmart.session.StatisticsProvider;

@Named("stats")
@RequestScoped
public class LocalStatisticsProvider implements StatisticsProvider {

    @Inject
    private CacheContainerProvider provider;

    public String getModifications() {
        return String.valueOf(-1);
    }

    public String getCreations() {
        return String.valueOf(-1);
    }

    public String getRemovals() {
        return String.valueOf(-1);
    }
}
