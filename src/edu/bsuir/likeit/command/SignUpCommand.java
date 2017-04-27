package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class SignUpCommand implements Command{
    private static final Logger LOG = LogManager.getLogger(SignUpCommand.class);
    private static final String COMMAND = "signup";
    private static final String SESSION_ATTR_USER = "user";
    private static final String URL_DEFAULT = "start.jsp";
    private static final String URL_PROFILE = "/controller?command=showprofile";
    private static final String REQUEST_PARAM_LOGIN = "login";
    private static final String REQUEST_PARAM_PASSWORD = "password";
    private static final String REQUEST_PARAM_EMAIL = "email";
    private static final String MESSAGE_EMPTY_FIELD = "emptyField";
    private static final String MESSAGE_LOGIN_NOT_FREE = "loginNotFree";

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new SignUpCommand();
        } else {
            return new EmptyCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from execute sign up");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (curUser != null) {
            return Optional.of(URL_DEFAULT);
        }
        String login = request.getParameter(REQUEST_PARAM_LOGIN);
        String password = request.getParameter(REQUEST_PARAM_PASSWORD);
        String email = request.getParameter(REQUEST_PARAM_EMAIL);
        try {
            if (login == null || password == null || email == null) {
                response.getWriter().write(MESSAGE_EMPTY_FIELD);
                return Optional.empty();
            }
            User newUser = new User(login, email, password);
            LOG.debug("start working with db");
            if(UserService.registerNewUser(newUser)) {
                session.setAttribute(SESSION_ATTR_USER, newUser);
                LOG.info("User " + newUser.getLogin() + " has signed up");
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
        return Optional.empty();
    }
}

