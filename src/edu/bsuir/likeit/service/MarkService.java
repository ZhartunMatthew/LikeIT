package edu.bsuir.likeit.service;

import edu.bsuir.likeit.dao.DAOException;
import edu.bsuir.likeit.dao.MarksDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MarkService {
    public static final Logger LOG = LogManager.getLogger(MarkService.class);
    public static void putMark(long userId, long authorId, long answerId, boolean mark) throws ServiceException {
        try {
            MarksDAO marksDAO = new MarksDAO();
            AnswerService answerService = new AnswerService();
            UserService userService = new UserService();
            int recentMark = marksDAO.takeUserMark(userId, answerId);
            LOG.debug("Resent mark: " + recentMark);
            LOG.debug("delete mark: " + recentMark);
            LOG.debug("author id: " + authorId);
            LOG.debug("user id" + userId);
            if(recentMark == 0) {
                marksDAO.putMark(userId, answerId, mark);
            } else {
                if((recentMark == 1)^mark) {
                    marksDAO.deleteMark(userId, answerId);

                } else {
                    return;
                }
            }
            answerService.changeAnswerRating(mark, answerId);
            userService.changeRating(authorId, mark);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
