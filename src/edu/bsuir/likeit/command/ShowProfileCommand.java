package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowProfileCommand implements Command{
    private static final String REQUEST_ATTR_USER = "user";
    private static final String URL_DEFAULT = "/controller?command=empty";
    private static final String URL_PROFILE = "/jsp/profile.jsp";
    private static final String COMMAND = "showprofile";
    private static final Logger LOG = LogManager.getLogger();

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new ShowProfileCommand();
        } else {
            return new EditProfileCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug(request.getParameter("userid"));
        Long id = Long.decode(request.getParameter("userid"));
        LOG.debug(id);
        try {
            User user = UserService.getUserById(id);
            LOG.debug(user.getLogin() + " " + user.getId()) ;
            request.setAttribute(REQUEST_ATTR_USER, user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return Optional.of(URL_PROFILE);
    }

    @Override
    public boolean needsRedirect() {
        return false;
    }
}
