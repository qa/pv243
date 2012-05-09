package cz.muni.fi.pv243.hawebapp;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HttpSessionServlet", urlPatterns = {"/session"})
public class HttpSessionServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(HttpSessionServlet.class.getName());
    public static final String KEY = HttpSessionServlet.class.getName();
    public static final String READONLY = "readonly";
    public static final String INVALIDATE = "invalidate";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO: Implement creating new session, storing SerialBean output the serial bean as plain text in response.

        // TODO: Optionally implement readonly scenario (only for the bored).

        // Invalidate?
        if (req.getParameter(HttpSessionServlet.INVALIDATE) != null) {
            // TODO: Invalidate the session here.
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet using Session to store SerialBean object with the serial.";
    }
}
