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
package cz.muni.fi.pv243.lesson7;

import java.io.IOException;
import java.net.InetAddress;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.RealmCallback;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

/**
 * WildFly8 Java API example - connect to remote WildFly8 instance
 * 
 * @author Rostislav Svoboda
 * @author Jitka Kozana
 */
public class RemoteConnectionApp {

    public static void main(String[] args) throws Exception {


        ModelControllerClient client = RemoteConnectionApp.createClient(
                InetAddress.getByName("127.0.0.1"), 9999, "ferda", "mravenec1*".toCharArray(), "ManagementRealm");


        ModelNode op = new ModelNode();
        op.get("operation").set("whoami");

        ModelNode returnVal = client.execute(op);

        System.out.println(returnVal.get("result").toString());

        client.close();
    }

    static ModelControllerClient createClient(final InetAddress host, final int port,
            final String username, final char[] password, final String securityRealmName) {

        final CallbackHandler callbackHandler = new CallbackHandler() {

            public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
                for (Callback current : callbacks) {
                    if (current instanceof NameCallback) {
                        NameCallback ncb = (NameCallback) current;
                        ncb.setName(username);
                    } else if (current instanceof PasswordCallback) {
                        PasswordCallback pcb = (PasswordCallback) current;
                        pcb.setPassword(password);
                    } else if (current instanceof RealmCallback) {
                        RealmCallback rcb = (RealmCallback) current;
                        rcb.setText(rcb.getDefaultText());
                    } else {
                        throw new UnsupportedCallbackException(current);
                    }
                }
            }
        };

        return ModelControllerClient.Factory.create(host, port, callbackHandler);
    }
}
