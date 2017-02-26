package edu.bsuir.likeit.dao;

import edu.bsuir.likeit.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO extends AbstractDAO {
    private static final String SELECT_USER_BY_ID = "SELECT * FROM USER WHERE ID=?";
    private static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM USER WHERE LOGIN=? AND PASSWORD=?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM USER WHERE LOGIN=?";
    private static final String INSERT_USER
            = "INSERT INTO USER (LOGIN, PASSWORD, EMAIL, ROLE, REG_DATE, RATING) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_USER_BY_ID = "DELETE FROM USER WHERE ID=?";
    private static final String CHAHGE_RATING = "UPDATE USER SET RATING = RATING + ? WHERE ID=?";
    private static final String UPDATE ="UPDATE USER SET LOGIN=?, EMAIL=?, PASSWORD=?, ROLE=? " +
            "WHERE ID=?";
    private static final Logger LOG = LogManager.getLogger();

    public UserDAO() throws DAOException {
    }

    public User update(User user) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole());
            ps.setLong(5, user.getId());
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DAOException("Error in update()", e);
        }
    }

    public User findByData(String login, String password) throws DAOException {
        try (PreparedStatement ps =
                         connection.prepareStatement(SELECT_USER_BY_LOGIN_PASSWORD)) {
                ps.setString(1, login);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new User(rs.getLong("ID"), rs.getString("LOGIN"),
                            rs.getString("EMAIL"), rs.getString("PASSWORD"),
                            rs.getDate("REG_DATE"), rs.getDate("DATE_ENABLE"), rs.getInt("RATING"),
                            rs.getInt("ROLE"));
                } else {
                    return null;
                }
        } catch (SQLException e) {
            throw new DAOException("Error in findByData()", e);
        }
    }

    public User findById(long id) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(id,
                        rs.getString("LOGIN"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getDate("REG_DATE"),
                        rs.getDate("DATE_ENABLE"),
                        rs.getInt("RATING"),
                        rs.getInt("ROLE"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error in findById()", e);
        }
    }

    public boolean isLoginFree(String login) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            boolean isFree = !rs.next();
            LOG.debug("Is Login Free: " + isFree);
            return isFree;
        } catch (SQLException e) {
            throw new DAOException("Error in isLoginFree()", e);
        }
    }

    public User registerNewUser(User user) throws DAOException {
        String generated[] = {"ID"};
        LOG.debug("DAO REGISTER NEW USER");
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, generated)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, 2);
            ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            ps.setLong(6, 0);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new DAOException("No ID obtained");
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException("Error in registerNewUser()", e);
        }
    }

    public void delete(User user) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID)) {
            ps.setLong(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delete()", e);
        }
    }

    public void delete(long id) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in delete()", e);
        }
    }

    public void changeRating(long id, int mark) throws DAOException {
        try (PreparedStatement ps = connection.prepareStatement(CHAHGE_RATING)) {
            ps.setInt(1, mark);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in changeRating()", e);
        }
    }
}
