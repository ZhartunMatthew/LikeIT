package edu.bsuir.likeit.connectionpool;

import java.util.ResourceBundle;

public class ConnectionPoolConfiguration {
    private static final String PATH_TO_PROPERTIES = "properties.database";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(PATH_TO_PROPERTIES);
    private static String url = getProperty("url");
    private static String user = getProperty("user");
    private static String password = getProperty("password");
    private static String poolCapacity = getProperty("pool.capacity");
    private static String timeWait = getProperty("time.wait");

    public ConnectionPoolConfiguration() {
    }

    static String getUrl() {
        return url;
    }

    static String getUser() {
        return user;
    }

    static String getPassword() {
        return password;
    }


    static int getPoolCapacity() {
        return Integer.valueOf(poolCapacity);
    }

    static int getTimeWait() {
        return Integer.valueOf(timeWait);
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
