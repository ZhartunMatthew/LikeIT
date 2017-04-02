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
import java.util.Optional;

public class EditProfileCommand implements Command {
    private static final String REQUEST_PARAM_LOGIN = "input_login";
    private static final String REQUEST_PARAM_PASSWORD = "old_pass";
    private static final String REQUEST_PARAM_NEW_PASSWORD = "new_pass";
    private static final String REQUEST_PARAM_NEW_EMAIL = "new_email";
    private static final String SESSION_ATTR_USER = "user";
    private static final String MESSAGE_FAIL = "fail";
    private static final String URL_DEFAULT = "/controller?command=empty";
    private static final String URL_PROFILE = "/controller?command=showprofile";
    private static final String COMMAND = "editprofile";
    private static final Logger LOG = LogManager.getLogger(EditProfileCommand.class);

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new EditProfileCommand();
        } else {
            return new DeleteAnswerCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from EditProfileCommand");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (curUser != null) {

            String password = request.getParameter(REQUEST_PARAM_PASSWORD);
            LOG.debug("LOGIN: " + request.getParameter(REQUEST_PARAM_LOGIN));
            LOG.debug(curUser.getPassword().equals(MD5Digest.encrypt(password)));
            if(curUser.getPassword().equals(MD5Digest.encrypt(password))) {
                String login = null;
                String newPassword = null;
                String newEmail = null;
                login = request.getParameter(REQUEST_PARAM_LOGIN);
                newPassword = MD5Digest.encrypt(request.getParameter(REQUEST_PARAM_NEW_PASSWORD));
                newEmail = request.getParameter(REQUEST_PARAM_NEW_EMAIL);

                LOG.debug( "Login: " + request.getParameter(REQUEST_PARAM_LOGIN) +
                        "\nPassword: " + request.getParameter(REQUEST_PARAM_NEW_PASSWORD) +
                        "\nEmail: " + request.getParameter(REQUEST_PARAM_NEW_EMAIL));
                try {
                    User user = new User(curUser.getId(), login, newEmail, newPassword);
                    UserService.updateUser(user);
                } catch (ServiceException e) {
                    throw new CommandException("Error while logging in", e);
                }
            }
        }
        return Optional.empty();
    }
}
