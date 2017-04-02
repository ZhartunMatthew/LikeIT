package edu.bsuir.likeit.command;

import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.MarkService;
import edu.bsuir.likeit.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class EstimateCommand  implements Command{
    private static final String COMMAND = "estimate";
    private static final Logger LOG = LogManager.getLogger(EstimateCommand.class);
    private static final String SESSION_ATTR_USER = "mark";
    private static final String UPDATE_URL = "/controller?command=showquestion&questionid=";


    public Command getCommand(HttpServletRequest request) throws CommandException {
        if(request.getParameter("command").equals(COMMAND)) {
            return new EstimateCommand();
        } else {
            return new AddQuestionCommand().getCommand(request);
        }
    }

    @Override
    public Optional<String> execute(HttpServletRequest request,
                                    HttpServletResponse response)
            throws CommandException {
        LOG.debug("hello from ESTIMATE");
        HttpSession session = request.getSession(true);
        long userId = ((User)session.getAttribute("user")).getId();
        int mark = Integer.decode(request.getParameter("mark"));
        long authorId = Long.decode(request.getParameter(("answer_author")));
        long answerId = Long.decode(request.getParameter(("answer")));
            try {
                if(mark == 1) {
                    MarkService.putMark(userId, authorId, answerId, true);
                } else {
                    MarkService.putMark(userId, authorId, answerId, false);
                }
                return Optional.of("/controller?command=showquestion&questionid=" +
                        request.getParameter(("question")).toString());
            } catch (ServiceException e) {
                throw new CommandException("Error while estimate", e);
            }
    }
}
