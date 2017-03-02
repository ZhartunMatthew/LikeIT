package edu.bsuir.likeit.service;

import edu.bsuir.likeit.dao.ContentDAO;
import edu.bsuir.likeit.dao.DAOException;
import edu.bsuir.likeit.entity.Content;

public class ContentService {
    public void delete(long questionId) throws ServiceException {
        try(ContentDAO contentDAO = new ContentDAO()) {
            contentDAO.delete(questionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void insert(Content content) throws ServiceException {
        try(ContentDAO contentDAO = new ContentDAO()) {
            contentDAO.insertContent(content);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static Content findByQuestionId(long questionId) throws ServiceException {
        try(ContentDAO contentDAO = new ContentDAO()) {
            return contentDAO.findByQuestionId(questionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Content update(Content content) throws ServiceException {
        try(ContentDAO contentDAO = new ContentDAO()) {
            return contentDAO.update(content);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
