package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.AnswerService;
import edu.bsuir.likeit.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class DeleteAnswerCommand implements Command{
    private static final String COMMAND = "deleteanswer";
    private static final Logger LOG = LogManager.getLogger(CreateThemeCommand.class);
    private static final String SESSION_ATTR_USER = "user";
    private static final String UPDATE_URL = "/controller?command=showquestion&questionid=";


    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new DeleteAnswerCommand();
        } else {
            return new DeleteQuestionCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from CREATE THEME");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        long questionId = Long.decode(request.getParameter("questionid").toString());
        if (curUser != null || curUser.getRole() == 1) {
            try {
                long id = Long.decode(request.getParameter("answerid").toString());
                AnswerService.deleteAnswer(id);
            } catch (ServiceException e) {
                throw new CommandException("Error while deleting answer", e);
            }
        }
        return Optional.of(UPDATE_URL + questionId);
    }
}
