package edu.bsuir.likeit.service;


import edu.bsuir.likeit.dao.DAOException;
import edu.bsuir.likeit.dao.ThemeDAO;
import edu.bsuir.likeit.entity.Theme;

import java.util.List;

public class ThemeService {
    public static Theme create(Theme theme) throws ServiceException {
        try(ThemeDAO dao = new ThemeDAO()) {
            return dao.create(theme);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(Theme theme) throws ServiceException {
        try(ThemeDAO dao = new ThemeDAO()) {
            dao.delete(theme);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static Theme findById(int id) throws ServiceException {
        try(ThemeDAO dao = new ThemeDAO()) {
            return dao.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public static List<Theme> getAll() throws ServiceException {
        try(ThemeDAO dao = new ThemeDAO()) {
            return dao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void change(Theme theme) throws ServiceException {
        try(ThemeDAO dao = new ThemeDAO()) {
            dao.update(theme);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
