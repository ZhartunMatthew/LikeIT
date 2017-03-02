package edu.bsuir.likeit.service;

import edu.bsuir.likeit.dao.AnswerDAO;
import edu.bsuir.likeit.dao.DAOException;
import edu.bsuir.likeit.entity.Answer;

import java.util.List;

public class AnswerService {

    public static Answer createAnswer(Answer answer) throws ServiceException {
        try(AnswerDAO dao = new AnswerDAO()) {
            return dao.createAnswer(answer);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static void deleteAnswer(long id) throws ServiceException {
        try(AnswerDAO dao = new AnswerDAO()) {
            dao.deleteAnswer(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static List<Answer> findAllAnswers(long questionId) throws ServiceException {
        try(AnswerDAO dao = new AnswerDAO()) {
            return dao.findByQuestionId(questionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void changeAnswerRating(boolean mark, long answerId) throws ServiceException {
        try(AnswerDAO dao = new AnswerDAO()) {
            dao.changeRating(mark, answerId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void changeAnswer(Answer answer) throws ServiceException {
        try(AnswerDAO dao = new AnswerDAO()) {
            dao.update(answer);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
