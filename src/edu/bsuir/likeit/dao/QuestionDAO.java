package edu.bsuir.likeit.dao;

import edu.bsuir.likeit.entity.Question;
import edu.bsuir.likeit.entity.Theme;
import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.ThemeService;
import edu.bsuir.likeit.service.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class QuestionDAO extends AbstractDAO {
    private static final String INSERT_QUESTION
            = "INSERT INTO QUESTION (TITLE, THEME_ID, USER_ID, CREATING_TIME) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM QUESTION WHERE ID=?";
    private static final String DELETE ="DELETE FROM QUESTION WHERE ID=?";
    private static final String UPDATE = "UPDATE QUESTION SET TITLE=? WHERE ID=?";
    private static final String FIND_ALL = "SELECT * FROM QUESTION";

    public QuestionDAO() throws DAOException {}

    public Question update(Question question) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, question.getTitle());
            ps.setLong(2, question.getId());
            ps.executeUpdate();
            return question;
        } catch (SQLException e) {
            throw new DAOException("Error in update()", e);
        }
    }

    public void delete(long id) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delete()", e);
        }
    }

    public LinkedList<Question> findAll() throws DAOException, ServiceException {
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            LinkedList<Question> list = new LinkedList<>();
            while(resultSet.next()) {
                User user = UserService.getUserById(resultSet.getLong("USER_ID"));
                Theme theme = ThemeService.findById(resultSet.getInt("THEME_ID"));
                list.add(new Question(resultSet.getLong("ID"), resultSet.getString("TITLE"),
                        theme, user, resultSet.getDate("CREATING_TIME")));
            }
            if(list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findAll()", e);
        }
    }

    public Question findById(long id) throws DAOException, ServiceException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                User user = UserService.getUserById(resultSet.getLong("USER_ID"));
                Theme theme = ThemeService.findById(resultSet.getInt("THEME_ID"));
                return new Question(resultSet.getLong("ID"), resultSet.getString("TITLE"),
                        theme, user,
                        resultSet.getDate("CREATING_TIME"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findById()", e);
        }
    }

    public Question createNewQuestion(Question question) throws DAOException {
        String generated[] = {"ID"};
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUESTION, generated)) {
            ps.setString(1, question.getTitle());
            ps.setLong(2, question.getTheme().getId());
            ps.setLong(3, question.getUser().getId());
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                question.setId(generatedKeys.getLong(1));
            } else {
                throw new DAOException("No ID obtained");
            }
            return question;
        } catch (SQLException e) {
            throw new DAOException("Error in createNewQuestion()", e);
        }
    }
}
