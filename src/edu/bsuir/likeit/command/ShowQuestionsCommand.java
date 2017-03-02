package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.Question;
import edu.bsuir.likeit.service.QuestionService;
import edu.bsuir.likeit.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ShowQuestionsCommand implements Command{
    private static final String REQUEST_ATTR_QUESTIONS = "questions";
    private static final String URL_DEFAULT = "/controller?command=empty";
    private static final String URL_ALL_QUESTIONS = "/index.jsp";
    private static final String COMMAND = "showallquestions";
    private static final Logger LOG = LogManager.getLogger();

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new ShowQuestionsCommand();
        } else {
            return new ShowQuestion().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        try {
            List<Question> allQuestions = new LinkedList<>();
            allQuestions = QuestionService.findAll();
            LOG.debug("RETURN TO COMMAND");
            request.setAttribute(REQUEST_ATTR_QUESTIONS, allQuestions);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return Optional.of(URL_ALL_QUESTIONS);
    }

    @Override
    public boolean needsRedirect() {
        return false;
    }
}
