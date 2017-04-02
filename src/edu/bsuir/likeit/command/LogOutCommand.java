package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Optional;

public class LogOutCommand  implements Command {
    private static final String DEFAULT_URL = "/controller?command=showallquestions";
    private static final String REQUEST_PARAM_USER = "user";
    private static final Logger LOG = LogManager.getLogger(LogOutCommand.class);
    private static final String COMMAND = "logout";

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new LogOutCommand();
        } else {
            return new CreateThemeCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(REQUEST_PARAM_USER);
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            session.removeAttribute(e.nextElement());
        }
        LOG.info("User " + (user != null ? user.getLogin() : "") +
                " has signed out.");
        return Optional.of(DEFAULT_URL);
    }
}
