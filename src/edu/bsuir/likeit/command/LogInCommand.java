package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.UserService;
import edu.bsuir.likeit.util.MD5Digest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LogInCommand implements Command {
    private static final String REQUEST_PARAM_LOGIN = "input_login";
    private static final String REQUEST_PARAM_PASSWORD = "input_password";
    private static final String SESSION_ATTR_USER = "user";
    private static final String MESSAGE_FAIL = "fail";
    private static final String URL_DEFAULT = "/controller?command=empty";
    private static final String URL_PROFILE = "/controller?command=showprofile&userid=";
    private static final String COMMAND = "login";
    private static final Logger LOG = LogManager.getLogger(LogInCommand.class);

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new LogInCommand();
        } else {
            return new ShowProfileCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from LOGIN");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (curUser != null) {
            return Optional.of(URL_DEFAULT);
        }
        String login = request.getParameter(REQUEST_PARAM_LOGIN);
        String password = request.getParameter(REQUEST_PARAM_PASSWORD);
        LOG.debug(login, password);
        try {
            User user =
                    UserService.getByData(login, new MD5Digest().encrypt(password));
            if (user != null) {
                session.setAttribute(SESSION_ATTR_USER, user);
                LOG.info("User " + user.getLogin() + " has logged in " + user.getId());
                return Optional.of(URL_PROFILE + user.getId());
            } else {
                response.getWriter().write(MESSAGE_FAIL);
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException("Error while logging in", e);
        }
        return Optional.empty();
    }
}
