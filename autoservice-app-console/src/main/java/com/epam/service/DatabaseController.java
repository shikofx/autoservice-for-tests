package com.epam.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseController {

    void createDatabase() throws ClassNotFoundException, SQLException;

    boolean connect() throws SQLException;

    boolean disconnect() throws SQLException;

    Connection getConnection();

}
