package edu.bsuir.likeit.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksDAO extends AbstractDAO {
    private static final String INSERT_MARK
            = "INSERT INTO MARKS (ANSWER_ID, USER_ID, VALUE) " +
            "VALUES (?, ?, ?)";
    private static final String SELECT_BY_USER_AND_ANSWER = "SELECT * FROM MARKS WHERE USER_ID=? AND ANSWER_ID=?";
    private static final String DELETE_BY_USER_AND_ANSWER = "DELETE FROM MARKS WHERE USER_ID=? AND ANSWER_ID=?";
    private static final Logger LOG = LogManager.getLogger(MarksDAO.class);


    public MarksDAO() throws DAOException {

    }

    public void checkMark(long userId, long answerId, boolean value) throws DAOException{
        AnswerDAO dao = new AnswerDAO();
        int recentMark = takeUserMark(userId, answerId);
        if(recentMark == 0) {
            putMark(userId, answerId, value);
            dao.changeRating(value, answerId);
        } else {
            if((recentMark == 1)^value) {
                deleteMark(userId, answerId);
                dao.changeRating(value, answerId);
            } else {
                return;
            }
        }
    }

    public void putMark(long userId, long answerId, boolean value) throws DAOException{
        String generated[] = {"ID"};
        try (PreparedStatement ps = connection.prepareStatement(INSERT_MARK, generated)) {
            ps.setLong(1, answerId);
            ps.setLong(2, userId);
            ps.setBoolean(3, value);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DAOException("No ID obtained");
            }
        } catch (SQLException e) {
            throw new DAOException("Error in putMark()", e);
        }
    }

    public void deleteMark(long userId, long answerId) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_USER_AND_ANSWER)) {
            ps.setLong(1, userId);
            ps.setLong(2, answerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in deleteMark()", e);
        }

    }

    public int takeUserMark(long userId, long answerId) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER_AND_ANSWER)) {
                ps.setLong(1, userId);
            ps.setLong(2, answerId);
            ResultSet rs = ps.executeQuery();
            LOG.debug("DAOMarks: "  + userId + " " + answerId);
            if(rs.next()) {
                if(rs.getBoolean("VALUE")) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in isUserAssessed()", e);
        }
    }


}
