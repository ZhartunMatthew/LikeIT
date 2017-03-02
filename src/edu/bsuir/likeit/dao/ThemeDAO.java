package edu.bsuir.likeit.dao;

import edu.bsuir.likeit.entity.Theme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Victoria on 31.01.2017.
 */
public class ThemeDAO extends AbstractDAO {
    private static final String INSERT = "INSERT INTO THEME (NAME) " +
        "VALUES (?)";
    private static final String SELECT_BY_ID = "SELECT * FROM THEME WHERE ID=?";
    private static final String SELECT_ALL = "SELECT * FROM THEME";
    private static final String DELETE_BY_ID = "DELETE FROM THEME WHERE ID=?";
    private static final String UPDATE ="UPDATE THEME SET NAME=? WHERE ID=?";
    public ThemeDAO() throws DAOException {

    }

    public LinkedList<Theme> getAll() throws DAOException{
        LinkedList<Theme> list = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                list.add(new Theme(resultSet.getLong("ID"), resultSet.getString("NAME")));
            }
            if(list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in getAll()", e);
        }
    }

    public Theme update(Theme theme) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, theme.getName());
            ps.setLong(2, theme.getId());
            ps.executeUpdate();
            return theme;
        } catch (SQLException e) {
            throw new DAOException("Error in update()", e);
        }
    }

    public void delete(Theme theme) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
            ps.setLong(1, theme.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delete()", e);
        }
    }

    public Theme findById(long id) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return new Theme(id, resultSet.getString("NAME"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findByQuestionId()", e);
        }
    }

    public Theme create(Theme theme) throws DAOException {
        String generated[] = {"ID"};
        try (PreparedStatement ps = connection.prepareStatement(INSERT, generated)) {
            ps.setString(1, theme.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                theme.setId(generatedKeys.getLong(1));
            } else {
                throw new DAOException("No ID obtained");
            }
            return theme;
        } catch (SQLException e) {
            throw new DAOException("Error in createNewAnswer()", e);
        }
    }

}
