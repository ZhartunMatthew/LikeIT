package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.Question;
import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.QuestionService;
import edu.bsuir.likeit.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by Victoria on 08.02.2017.
 */
public class DeleteQuestionCommand implements Command{
    private static final String COMMAND = "deletequestion";
    private static final Logger LOG = LogManager.getLogger(DeleteQuestionCommand.class);
    private static final String SESSION_ATTR_USER = "user";
    private static final String QUESTIONS_URL = "/controller?command=showallquestions";
    private static final String START_URL = "/start.jsp";


    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new DeleteQuestionCommand();
        } else {
            return new LogOutCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from CREATE QUESTION");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if(curUser != null) {
            try {
                long questionId = Long.decode(request.getParameter("questionid").toString());
                Question question = QuestionService.findByQuestionId(questionId);
                if (question.getUser().getId() == curUser.getId() ||
                        curUser.getRole() == 1) {
                    QuestionService.delete(question);
                    return Optional.of(QUESTIONS_URL);
                }
            } catch (ServiceException e) {
                throw new CommandException("Error while deleting question", e);
            }
        }

        return Optional.of(START_URL);
    }
}
