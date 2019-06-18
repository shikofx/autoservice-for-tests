package com.epam.controller;

import com.epam.service.DatabaseController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDataBaseController implements DatabaseController {

    private final String dbUrl;
    private Connection connection;


    public DerbyDataBaseController(String dbName) throws SQLException, ClassNotFoundException {
        String DERBY_PROTOCOL = "jdbc:derby:";
        this.dbUrl = String.format("%s%s;create=true", DERBY_PROTOCOL, dbName);
        this.createDatabase();
        connection = null;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void createDatabase() throws ClassNotFoundException, SQLException {
        String DERBY_JDBC_EMBEDDED_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(DERBY_JDBC_EMBEDDED_DRIVER);
        connect();
    }

    @Override
    public boolean connect() throws SQLException {

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(dbUrl);
        }
        return !connection.isClosed();
    }

    @Override
    public boolean disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        return connection == null || connection.isClosed();
    }

}
