package edu.bsuir.likeit.dao;


import edu.bsuir.likeit.entity.Content;
import edu.bsuir.likeit.entity.elementsofcontent.ElementOfContent;
import edu.bsuir.likeit.entity.elementsofcontent.Image;
import edu.bsuir.likeit.entity.elementsofcontent.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ContentDAO extends AbstractDAO {
    private static final String SELECT_BY_QUESTION = "SELECT * FROM CONTENT WHERE QUESTION_ID=? ORDER BY PRIORITY";
    private static final String INSERT_CONTENT = "INSERT INTO CONTENT (QUESTION_ID, TEXT, IMAGE, PRIORITY) " +
            "VALUES (?, ?, ?, ?)";
    private static final String DELETE_BY_QUESTION_ID = "DELETE FROM CONTENT WHERE QUESTION_ID=?";
    private static final String UPDATE ="UPDATE CONTENT SET TEXT=? AND IMAGE=?" +
            "WHERE QUESTION_ID=? AND PRIORITY=?";

    public ContentDAO() throws DAOException {
    }

    public Content update(Content content) throws DAOException {
        String generated[] = {"ID"};
        int priority = 1;
        for (ElementOfContent element : content.getContentList()) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
                ps.setLong(3, content.getQuestionId());
                ps.setInt(4, priority);
                element.prepareUpdateStatement(ps);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException("Error in registerNewUser()", e);
            }
        }
        return content;
    }

    public void delete(long questionId) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_QUESTION_ID)) {
            ps.setLong(1, questionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delete()", e);
        }

    }

    public Content findByQuestionId(long questionID) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_QUESTION)) {
            ps.setLong(1, questionID);
            ResultSet rs = ps.executeQuery();
            LinkedList<ElementOfContent> list = new LinkedList<>();
            while (rs.next()) {
                if(rs.getBlob("IMAGE") != null) {
                    list.add(new Image(rs.getBlob("IMAGE")));
                } else {
                    if(rs.getBlob("TEXT") != null) {
                        list.add(new Text(rs.getString("TEXT")));
                    }
                }
            }
            if(list.isEmpty()) {
                return null;
            } else {
                return new Content(questionID, list);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findById()", e);
        }
    }

    public Content insertContent(Content content) throws DAOException {
        String generated[] = {"ID"};
        int priority = 1;
        for (ElementOfContent element : content.getContentList()) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_CONTENT, generated)) {
                ps.setLong(1, content.getQuestionId());
                ps.setInt(4, priority);
                element.prepareStatement(ps);
                ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    priority++;
                } else {
                    throw new DAOException("No ID obtained");
                }
            } catch (SQLException e) {
                throw new DAOException("Error in registerNewUser()", e);
            }
        }
        return content;
    }
}
