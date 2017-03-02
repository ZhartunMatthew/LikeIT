package edu.bsuir.likeit.servlet;

import edu.bsuir.likeit.command.Command;
import edu.bsuir.likeit.command.CommandException;
import edu.bsuir.likeit.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {
        private static final Logger LOG = LogManager.getLogger(Controller.class);
        private static final String REQUEST_ATTR_EXCEPTION = "exception";
        private static final String REQUEST_ATTR_MESSAGE = "message";

        static {
            LOG.debug("Hello from controller!");
            System.out.println("CONTROLLER");
        }

        public Controller() {
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            processRequest(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);
        }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LOG.debug("Processed request...");
            Command command = CommandFactory.defineCommand(request);
            Optional<String> page = command.execute(request, response);
            if (page.isPresent()) {
                if (command.needsRedirect()) {
                    response.sendRedirect(page.get());
                } else {
                    getServletContext()
                            .getRequestDispatcher(page.get())
                            .forward(request, response);
                }
            }
        } catch (CommandException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            LOG.error(sw.toString(), e);
            request.setAttribute(REQUEST_ATTR_EXCEPTION, e.getClass().toString());
            request.setAttribute(REQUEST_ATTR_MESSAGE, e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
