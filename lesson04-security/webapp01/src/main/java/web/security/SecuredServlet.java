/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2011, Red Hat, Inc., and individual contributors
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
package web.security;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.security.TestBean;

/**
 * A simple servlet that just writes back a string
 *
 */
@WebServlet(name = "SecuredServlet", urlPatterns = { "/secured/" }, loadOnStartup = 1)
@ServletSecurity(@HttpConstraint(rolesAllowed = { "gooduser" }))
@DeclareRoles("gooduser")
public class SecuredServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private TestBean testBean;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        writer.write("GOOD - beginning of doGet on servlet\n");
        
        try {
            writer.write(testBean.echo("successful call to echo method\n"));
        }
        catch (EJBAccessException e) {
            writer.write("call to echo method failed\n");
        }

        try {
            writer.write(testBean.goodUserEcho("successful call to goodUserEcho method\n"));
        }
        catch (EJBAccessException e) {
            writer.write("call to goodUserEcho method failed\n");
        }

        try {
            writer.write(testBean.superUserEcho("successful call to superUserEcho method\n"));
        }
        catch (EJBAccessException e) {
            writer.write("call to superUserEcho method failed\n");
        }

        
    }
}
