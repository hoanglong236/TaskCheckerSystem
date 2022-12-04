package org.swing.app.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "TaskCheckerSystem";
    private static final String DB_USER = "myapp";
    private static final String DB_SECRET = "a123456a";

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static Connection UNIQUE_CONNECTION = null;

    public static Connection getConnection() {
        if (UNIQUE_CONNECTION == null) {
            final StringBuilder databaseUrl = new StringBuilder();
            databaseUrl.append("jdbc:mysql://");
            databaseUrl.append(DB_HOST);
            databaseUrl.append(":");
            databaseUrl.append(DB_PORT);
            databaseUrl.append("/");
            databaseUrl.append(DB_NAME);

            try {
                Class.forName(DRIVER_CLASS_NAME);
                UNIQUE_CONNECTION = DriverManager.getConnection(databaseUrl.toString(), DB_USER, DB_SECRET);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return UNIQUE_CONNECTION;
    }
}
