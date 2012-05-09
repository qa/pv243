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

import cz.muni.fi.pv243.lesson06.ejb.remote.stateless.StatelessRemote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Ondrej Chaloupka
 */
public class StatelessRemoteClient {

	public static void main(String[] args) throws Exception {
		StatelessRemote statelessBean = lookupStatelessRemote();
		int interations = args.length > 0 ? Integer.parseInt(args[0]) : 100;

		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Semaphore printingSemaphore = new Semaphore(1);
		Map<String, Integer> numberOfCalls = new HashMap<String, Integer>();

		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable worker = new PrinterRunnable(numberOfCalls, printingSemaphore);
		executor.execute(worker);
		
		while (!"q".equals(input.trim())) {

			for (int i = 0; i < interations; i++) {
				String nodeName = statelessBean.getNodeName();
				Integer calls = numberOfCalls.get(nodeName);
				numberOfCalls.put(nodeName, calls == null ? 1 : ++calls);
				Thread.sleep(5);
			}

			printingSemaphore.acquire();
			System.out.print("To exit enter 'q', to continue hit ENTER: ");
			input = br.readLine();
			printingSemaphore.release();
		}

		executor.shutdownNow(); // calling interrupt()
		// Wait for executors to finish
		while (!executor.isTerminated()) {}
	}

	/**
	 * Do remote lookup.
	 * 
	 * @see https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
	 */
	private static StatelessRemote lookupStatelessRemote()
			throws NamingException {
		final Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		final Context ctx = new InitialContext(jndiProperties);

		return (StatelessRemote) ctx
				.lookup("ejb:/lesson6-server-side-1.0.0-SNAPSHOT//StatelessBean!" + StatelessRemote.class.getName());
	}

}
