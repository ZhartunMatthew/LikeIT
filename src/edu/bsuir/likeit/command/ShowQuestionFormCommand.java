package edu.bsuir.likeit.command;


import edu.bsuir.likeit.entity.Theme;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.ThemeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ShowQuestionFormCommand implements Command{
    private static final String REQUEST_ATTR_THEMES = "themes";
    private static final String URL_DEFAULT = "/controller?command=empty";
    private static final String COMMAND = "showquestionform";
    private static final Logger LOG = LogManager.getLogger(ShowQuestionFormCommand.class);

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new ShowQuestionFormCommand();
        } else {
            return new LogInCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        try {
            List<Theme> list = new LinkedList<>();
            list = ThemeService.getAll();
            LOG.debug(list);
            request.setAttribute(REQUEST_ATTR_THEMES, list);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return Optional.of("/jsp/ask_question.jsp");
    }

    @Override
    public boolean needsRedirect() {
        return false;
    }
}
