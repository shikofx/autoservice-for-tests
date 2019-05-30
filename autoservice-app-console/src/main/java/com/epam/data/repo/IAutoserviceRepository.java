package com.epam.data.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

interface IAutoserviceRepository<T> {

    void createTable(T o) throws SQLException;


    void deleteTable(T o) throws SQLException;

    boolean isTableExists(T o) throws SQLException;

    void add(T object) throws SQLException, IllegalAccessException;

    List<T> findAll() throws SQLException;

    void executeUpdate(String query) throws SQLException;

    T fromSqlResult(ResultSet res) throws SQLException;
}
