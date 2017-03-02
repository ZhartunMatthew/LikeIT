package edu.bsuir.likeit.dao;

import edu.bsuir.likeit.entity.Answer;
import edu.bsuir.likeit.entity.User;
import edu.bsuir.likeit.service.ServiceException;
import edu.bsuir.likeit.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Victoria on 28.01.2017.
 */
public class AnswerDAO extends AbstractDAO {
    private static final String INSERT_ANSWER
            = "INSERT INTO ANSWER (QUESTION_ID, TEXT, USER_ID, CREATING_TIME, RATING) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_QUESTION_ID = "SELECT * FROM ANSWER WHERE QUESTION_ID=?";
    private static final String CHANGE_RATING = "UPDATE ANSWER SET RATING = RATING + ? WHERE ID=?";
    private static final String DELETE_ANSWER_BY_ID = "DELETE FROM ANSWER WHERE ID=?";
    private static final String UPDATE_ANSWER ="UPDATE ANSWER SET TEXT=?" +
                                            "WHERE ID=?";
    private static final Logger LOG = LogManager.getLogger();

    public AnswerDAO() throws DAOException {}

    public Answer update(Answer answer) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ANSWER)) {
            ps.setString(1, answer.getAnswer());
            ps.setLong(2, answer.getId());
            ps.executeUpdate();
            return answer;
        } catch (SQLException e) {
            throw new DAOException("Error in update()", e);
        }
    }

    public void deleteAnswer(long id) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_ANSWER_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delete()", e);
        }
    }

    public LinkedList<Answer> findByQuestionId(long id) throws DAOException, ServiceException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_QUESTION_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            LinkedList<Answer> answers = new LinkedList<>();
            while(resultSet.next()) {
                User user = UserService.getUserById(resultSet.getLong("USER_ID"));
                answers.add(new Answer(resultSet.getLong("ID"), resultSet.getLong("QUESTION_ID"),
                        resultSet.getString("TEXT"), user, resultSet.getDate("CREATING_TIME"),
                        resultSet.getLong("RATING")));
            }
            if(answers.isEmpty()) {
                return null;
            } else {
                return answers;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findByQuestionId()", e);
        }
    }

    public Answer createAnswer(Answer answer) throws DAOException {
        String generated[] = {"ID"};
        try (PreparedStatement ps = connection.prepareStatement(INSERT_ANSWER, generated)) {
            ps.setLong(1, answer.getQuestionId());
            ps.setString(2, answer.getAnswer());
            ps.setLong(3, answer.getUser().getId());
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.setLong(5, 0);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                answer.setId(generatedKeys.getLong(1));
            } else {
                throw new DAOException("No ID obtained");
            }
            return answer;
        } catch (SQLException e) {
            throw new DAOException("Error in createNewAnswer()", e);
        }
    }

    public void changeRating(boolean mark, long answerId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(CHANGE_RATING)) {
            if(mark) {
                ps.setInt(1, 1);
            } else {
                ps.setInt(1, -1);
            }
            ps.setLong(2, answerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in changeRating()", e);
        }
    }
}
