package edu.bsuir.likeit.command;


import edu.bsuir.likeit.entity.Theme;
import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.ThemeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CreateThemeCommand implements Command{
    private static final String COMMAND = "createtheme";
    private static final Logger LOG = LogManager.getLogger(CreateThemeCommand.class);
    private static final String SESSION_ATTR_USER = "user";
    private static final String DEFAULT_URL = "/controller?command=showallquestions";


    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new CreateThemeCommand();
        } else {
            return new ChangeLanguageCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from CREATE THEME");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (curUser != null && curUser.getRole() == 1) {
            try {
                LOG.debug(request.getParameter("new_theme"));
                ThemeService.create(new Theme(request.getParameter("new_theme")));
            } catch (ServiceException e) {
                throw new CommandException("Error while create question", e);
            }
        }
        return Optional.of(DEFAULT_URL);
    }
}
