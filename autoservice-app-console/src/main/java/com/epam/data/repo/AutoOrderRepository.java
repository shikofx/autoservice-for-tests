package com.epam.data.repo;

import static com.epam.domain.SqlEntity.SQL_CREATE_TABLE;
import static com.epam.domain.SqlEntity.SQL_DROP_TABLE;
import static com.epam.domain.SqlEntity.SQL_INSERT_INTO;

import com.epam.console.ConsoleDataFormat;
import com.epam.domain.AutoOrder;
import com.epam.service.DatabaseController;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoOrderRepository implements IAutoserviceRepository<AutoOrder> {

    private DatabaseController databaseController;

    public AutoOrderRepository(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @Override
    public void createTable(AutoOrder order) throws SQLException {
        databaseController.connect();
        String
            query =
            String.format(String.format("%s %s(%s)", SQL_CREATE_TABLE, order.dbTableName(), order.dbColumnsName()));
        if (!isTableExists(order)) {
            executeUpdate(query);
        }
        databaseController.disconnect();
    }

    @Override
    public void deleteTable(AutoOrder order) throws SQLException {
        databaseController.connect();
        if (isTableExists(order)) {
            String query = String.format("%s %s", SQL_DROP_TABLE, order.dbTableName());
            executeUpdate(query);
        }
        databaseController.disconnect();
    }

    @Override
    public void add(AutoOrder order) throws SQLException, IllegalAccessException {
        databaseController.connect();
        String
            query =
            String.format(String.format("%s  %s VALUES (%s)", SQL_INSERT_INTO, order.dbTableName(), order.dbValues()));
        if (isTableExists(order)) {
            this.executeUpdate(query);
        }
    }

    @Override
    public List<AutoOrder> findAll() throws SQLException {
        List<AutoOrder> orders = new ArrayList<>();
        databaseController.connect();
        Statement sta = databaseController.getConnection().createStatement();
        String query = "SELECT * FROM AUTOORDER";
        ResultSet res = sta.executeQuery(query);
        while (res.next()) {
            orders.add(fromSqlResult(res));
        }
        res.close();
        sta.close();
        return orders;
    }

    @Override
    public void executeUpdate(String query) throws SQLException {
        Statement pStatement = databaseController.getConnection().createStatement();
        pStatement.executeUpdate(query);
        pStatement.close();
    }

    @Override
    public boolean isTableExists(AutoOrder order) throws SQLException {
        boolean isExists = false;
        String tableName = order.dbTableName().toUpperCase();
        databaseController.connect();
        if (databaseController.getConnection() != null) {
            DatabaseMetaData metaData = databaseController.getConnection().getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                isExists = true;
            }
        }
        return isExists;
    }

    @Override
    public AutoOrder fromSqlResult(ResultSet res) throws SQLException {
        AutoOrder order = new AutoOrder();
        order.setId(res.getInt("ID"));
        order.setDate(new ConsoleDataFormat().fullDateStringToDate(res.getString("DATE")));
        //String s = res.getString("OWNERNAME");
        order.setOwnerName(res.getString("ownerName"));
        return order;
    }
}
