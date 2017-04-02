package edu.bsuir.likeit.command;


import edu.bsuir.likeit.entity.Content;
import edu.bsuir.likeit.entity.Question;
import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.QuestionService;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.ThemeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Optional;

public class AddQuestionCommand implements Command{
    private static final String REQUEST_PARAM_TITLE = "title";
    private static final String REQUEST_PARAM_THEME = "themeid";
    private static final String REQUEST_PARAM_PROBLEM = "problem";
    private static final String SESSION_ATTR_USER = "user";
    private static final String MESSAGE_FAIL = "fail";
    private static final String URL_DEFAULT = "/controller?command=empty";
    private static final String URL_QUESTION = "/controller?command=showquestion&questionid=";
    private static final String COMMAND = "addquestion";
    private static final Logger LOG = LogManager.getLogger(LogInCommand.class);

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new AddQuestionCommand();
        } else {
            return new AddAnswerCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from ADD QUESTION");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (curUser != null) {
            try {
                LOG.debug(request.getParameter(REQUEST_PARAM_TITLE));
                int themeId = Integer.parseInt(request.getParameter(REQUEST_PARAM_THEME).toString());
                Question question = new Question(request.getParameter(REQUEST_PARAM_TITLE).toString(),
                        ThemeService.findById(themeId),
                        curUser,
                        new Date(System.currentTimeMillis()));
                Content content = new Content(request.getParameter(REQUEST_PARAM_PROBLEM).toString());
                long questionId = QuestionService.insert(content, question).getId();
                return Optional.of(URL_QUESTION + questionId);
            } catch (ServiceException e) {
                throw new CommandException("Error while create question", e);
            }
        }
        return Optional.empty();
    }
}
