package edu.bsuir.likeit.command;


import edu.bsuir.likeit.entity.Answer;
import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.AnswerService;
import edu.bsuir.likeit.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Optional;

public class AddAnswerCommand implements Command {

    private static final String REQUEST_PARAM_ANSWER = "new_answer";
    private static final String REQUEST_PARAM_QUESTION = "questionid";
    private static final String SESSION_ATTR_USER = "user";
    private static final String MESSAGE_FAIL = "fail";
    private static final String URL_DEFAULT = "/controller?command=empty";
    private static final String URL_QUESTION = "/controller?command=showquestion&questionid=";
    private static final String COMMAND = "addanswer";
    private static final Logger LOG = LogManager.getLogger(LogInCommand.class);

    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new AddAnswerCommand();
        } else {
            return new ShowQuestionFormCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from ADD ANSWER");
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute(SESSION_ATTR_USER);
        if (curUser != null) {
            try {
                long questionId = Integer.parseInt(request.getParameter(REQUEST_PARAM_QUESTION).toString());
                Answer answer = new Answer(questionId,
                        request.getParameter(REQUEST_PARAM_ANSWER).toString(),
                        curUser,
                        new Date(System.currentTimeMillis()),
                        0);
                AnswerService.createAnswer(answer);
                LOG.debug(answer);
                return Optional.of(URL_QUESTION + questionId);
            } catch (ServiceException e) {
                throw new CommandException("Error while create answer", e);
            }
        }
        return Optional.empty();
    }
}
