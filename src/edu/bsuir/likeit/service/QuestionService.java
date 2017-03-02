package edu.bsuir.likeit.service;

import edu.bsuir.likeit.dao.ContentDAO;
import edu.bsuir.likeit.dao.DAOException;
import edu.bsuir.likeit.dao.QuestionDAO;
import edu.bsuir.likeit.entity.Content;
import edu.bsuir.likeit.entity.Question;

import java.util.LinkedList;

public class QuestionService {
    public static void delete(Question question) throws ServiceException {
        try (QuestionDAO questionDAO = new QuestionDAO()){
            ContentDAO contentDAO = new ContentDAO();
            contentDAO.delete(question.getId());
            questionDAO.delete(question.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static Question insert(Content content, Question question) throws ServiceException {
        try(QuestionDAO questionDAO = new QuestionDAO()) {
            ContentService contentService = new ContentService();
            question = questionDAO.createNewQuestion(question);
            content.setQuestionId(question.getId());
            contentService.insert(content);
            return question;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static LinkedList<Question> findAll() throws ServiceException {
        try(QuestionDAO questionDAO = new QuestionDAO()) {
            return questionDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static Question findByQuestionId(long questionId) throws ServiceException {
        try(QuestionDAO questionDAO = new QuestionDAO()) {
            return questionDAO.findById(questionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Question update(Question question, Content content) throws ServiceException {
        try(QuestionDAO questionDAO = new QuestionDAO()) {
            ContentService contentService = new ContentService();
            contentService.update(content);
            return questionDAO.update(question);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
