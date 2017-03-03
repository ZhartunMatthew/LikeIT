package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.Answer;
import edu.bsuir.likeit.entity.Content;
import edu.bsuir.likeit.entity.Question;
import edu.bsuir.likeit.entity.elementsofcontent.ElementOfContent;
import edu.bsuir.likeit.entity.elementsofcontent.Text;
import edu.bsuir.likeit.service.AnswerService;
import edu.bsuir.likeit.service.ContentService;
import edu.bsuir.likeit.service.QuestionService;
import edu.bsuir.likeit.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ShowQuestion implements Command {
    private static final String COMMAND = "showquestion";
    private static final String REQUEST_ATTR_QUESTION = "question";
    private static final String REQUEST_ATTR_CONTENT = "content";
    private static final String REQUEST_ATTR_ANSWERS = "answers";
    private static final String NEW_URL = "/jsp/question.jsp";
    private static final Logger LOG = LogManager.getLogger();


    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new ShowQuestion();
        } else {
            //next command
            return new EstimateCommand().getCommand(request);
        }
    }

    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        try {
            LOG.debug("REQ: " + request);
            long questionId = Long.valueOf(request.getParameter("questionid"));
            Question question = new QuestionService().findByQuestionId(questionId);
            Content content = ContentService.findByQuestionId(questionId);
            String insertCode = new String();
            for (ElementOfContent item : content.getContentList()) {
                if(item.getClass() == Text.class) {
                    LOG.debug(((Text)item).getText());
                    insertCode = insertCode + "<p>" + item.toString() + "</p>";
                }
            }
            LOG.debug("INSERT" + insertCode);
            request.setAttribute(REQUEST_ATTR_QUESTION, question);
            request.setAttribute(REQUEST_ATTR_CONTENT, insertCode);
            List<Answer> list = new LinkedList<>();
            list = new AnswerService().findAllAnswers(questionId);
            request.setAttribute(REQUEST_ATTR_ANSWERS, list);
            return Optional.of(NEW_URL);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public boolean needsRedirect() {
        return false;
    }
}
