package org.swing.app.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final Logger LOGGER = LogManager.getLogger(MySQLConnection.class);

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "TaskCheckerSystem";
    private static final String DB_USER = "myapp";
    private static final String DB_SECRET = "a123456a";

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static Connection UNIQUE_CONNECTION = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (UNIQUE_CONNECTION == null) {
            final StringBuilder databaseUrl = new StringBuilder();

            databaseUrl.append("jdbc:mysql://");
            databaseUrl.append(DB_HOST);
            databaseUrl.append(":");
            databaseUrl.append(DB_PORT);
            databaseUrl.append("/");
            databaseUrl.append(DB_NAME);

            Class.forName(DRIVER_CLASS_NAME);
            UNIQUE_CONNECTION = DriverManager.getConnection(databaseUrl.toString(), DB_USER, DB_SECRET);
            LOGGER.info("Create connection success");
        }

        return UNIQUE_CONNECTION;
    }
}
