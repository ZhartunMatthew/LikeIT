package edu.bsuir.likeit.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
        private static ConnectionPool instance;
        private static Lock lock = new ReentrantLock();
        private static AtomicBoolean created = new AtomicBoolean(false);
        private static AtomicBoolean releasing = new AtomicBoolean(false);
        private static final int POOL_CAPACITY = ConnectionPoolConfiguration.getPoolCapacity();
        private static final int TIME_WAIT = ConnectionPoolConfiguration.getTimeWait();
        private ArrayBlockingQueue<ProxyConnection> connections;
        private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);

        static {
            LOG.debug("hello log4j2");
        }

        private ConnectionPool() {
            registerDriver();
            String url = ConnectionPoolConfiguration.getUrl();
            String user = ConnectionPoolConfiguration.getUser();
            String password = ConnectionPoolConfiguration.getPassword();
            connections = new ArrayBlockingQueue<>(POOL_CAPACITY);
            for (int i = 0; i < POOL_CAPACITY; i++) {
                addNewConnection(url, user, password);
            }
            for (int i = connections.size(); i < POOL_CAPACITY; i++) { // second attempt
                addNewConnection(url, user, password);
            }
            if (connections.isEmpty()) {
                LOG.fatal("Initializing connection pool failed");
                throw new ConnectionPoolInitException("Initializing connection pool failed");
            }
        }

        private void registerDriver() {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            } catch (SQLException e) {
                LOG.fatal("Couldn't register driver", e);
                throw new ConnectionPoolInitException("Couldn't register driver", e);
            }
        }

        private void deregisterDrivers() {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while(drivers.hasMoreElements()){
                Driver driver = drivers.nextElement();
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    LOG.error("Couldn't deregister driver: " + driver, e);
                }
            }
        }

        private void addNewConnection(String url, String user, String password) {
            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                ProxyConnection proxyConn = new ProxyConnection(conn);
                connections.add(proxyConn);
            } catch (SQLException e) {
                LOG.error("Couldn't create connection", e);
            }
        }

        public static ConnectionPool getInstance() {
            if (!created.get()) {
                lock.lock();
                if (!created.get()) {
                    try {
                        instance = new ConnectionPool();
                        created.set(true);
                    } finally {
                        lock.unlock();
                    }
                }
            }
            return instance;
        }

        public ProxyConnection getConnection() throws ConnectionPoolException {
            if (releasing.get()) {
                throw new ConnectionPoolException("Cannot get connection when pool is releasing");
            }
            try {
                ProxyConnection connection =
                        connections.poll(TIME_WAIT, TimeUnit.MILLISECONDS);
                if (connection != null) {
                    return connection;
                } else {
                    throw new ConnectionPoolException("Timeout when waiting for connection");
                }
            } catch (InterruptedException e) {
                LOG.error("Error when waiting for connection", e);
                return null;
            }
        }

        public void releaseConnection(ProxyConnection connection) {
            if (connection != null) {
                try {
                    if (!connection.getAutoCommit()) {
                        connection.rollback();
                    }
                    connections.add(connection);
                } catch (SQLException e) {
                    LOG.error("Error when releasing connection", e);
                }
            }
        }

        public void releasePool() {
            while (!connections.isEmpty()) {
                ProxyConnection connection;
                if ((connection = connections.poll()) != null) {
                    try {
                        connection.trueClose();
                    } catch (SQLException e) {
                        LOG.error("Error when releasing pool", e);
                    }
                }
            }
            deregisterDrivers();
        }
}
