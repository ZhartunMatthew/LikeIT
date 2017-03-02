package edu.bsuir.likeit.service;

import edu.bsuir.likeit.dao.DAOException;
import edu.bsuir.likeit.dao.UserDAO;
import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.util.MD5Digest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {

    public static final Logger LOG = LogManager.getLogger(UserService.class);

    public static boolean registerNewUser(User user) throws ServiceException {
        try(UserDAO dao = new UserDAO()) {
            boolean isLoginFree = dao.isLoginFree(user.getLogin());
            if(isLoginFree) {
                user.setPassword(MD5Digest.encrypt(user.getPassword()));
                dao.registerNewUser(user);
                LOG.debug("return true");
                return true;
            } else {
                LOG.debug("return false");
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static void deleteUser(User user) throws ServiceException {
        try(UserDAO dao = new UserDAO()) {
            dao.delete(user.getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static User getUserById(long id) throws ServiceException {
        try(UserDAO dao = new UserDAO()) {
            return dao.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static User getByData(String login, String password) throws ServiceException {
        try(UserDAO dao = new UserDAO()) {
            return dao.findByData(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static void changeRating(long userId, boolean mark) throws ServiceException {
        try(UserDAO dao = new UserDAO()) {
            if(mark) {
                dao.changeRating(userId, 1);
            } else {
                dao.changeRating(userId, -1);

            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static void updateUser(User user) throws ServiceException {
        try(UserDAO dao = new UserDAO()) {
            dao.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
