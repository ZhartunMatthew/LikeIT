package edu.bsuir.likeit.dao;


import edu.bsuir.likeit.connectionpool.ConnectionPool;
import edu.bsuir.likeit.connectionpool.ConnectionPoolException;
import edu.bsuir.likeit.connectionpool.ProxyConnection;

abstract class AbstractDAO implements AutoCloseable {
    ProxyConnection connection;

    AbstractDAO() throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            System.out.println("new connection " + getClass());
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Returns DAO's connection to pool.
     */
    @Override
    public void close() {
        System.out.println("close);");
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
