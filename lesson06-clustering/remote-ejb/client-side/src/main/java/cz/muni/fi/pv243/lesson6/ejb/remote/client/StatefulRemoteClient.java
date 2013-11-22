/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
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

package cz.muni.fi.pv243.lesson6.ejb.remote.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cz.muni.fi.pv243.lesson06.ejb.remote.stateful.StatefulRemote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;


public class StatefulRemoteClient {

	public static void main(String[] args) throws Exception {
		StatefulRemote statefulBean = lookupStatefulRemote();
		
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                // TODO: implement sending string to server and lookuping the list of them
                // TODO: watch how the cluster behaves when one of the nodes goes down
	}

	/**
	 * Do remote lookup.
	 * 
	 * @see https ://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
	 */
	private static StatefulRemote lookupStatefulRemote() throws NamingException {
		final Properties jndiProperties = new Properties();
                // TODO: set up properties
		final Context ctx = new InitialContext(jndiProperties);

                // TODO: jndi lookup for stateful bean
	}

}
