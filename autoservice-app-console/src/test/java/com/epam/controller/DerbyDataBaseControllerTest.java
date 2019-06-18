package com.epam.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;

public class DerbyDataBaseControllerTest {

    private String dbName;
    private DerbyDataBaseController databaseController;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        dbName = "test_base";
        databaseController = new DerbyDataBaseController(dbName);

    }

    @After
    public void tearDown() throws SQLException {
        databaseController.disconnect();
    }

    @Test
    public void databaseCanBeCreated() {
        File databaseFolder = new File(dbName);
        Assert.assertTrue(databaseFolder.exists());
    }

    @Test
    public void connectionCanBeCreated() throws SQLException {
        Assert.assertTrue(databaseController.connect());

    }

    @Test
    public void connectionCanBeClosed() throws SQLException {
        databaseController.connect();
        Assert.assertTrue(databaseController.disconnect());
    }
}