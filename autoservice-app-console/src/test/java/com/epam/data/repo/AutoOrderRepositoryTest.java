package com.epam.data.repo;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.epam.console.ConsoleDataFormat;
import com.epam.controller.DerbyDataBaseController;
import com.epam.domain.AutoOrder;
import com.epam.service.DatabaseController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoOrderRepositoryTest {

    private AutoOrder autoOrder;
    private AutoOrderRepository orderRepository;
    private List<AutoOrder> ordersList;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {

        autoOrder = new AutoOrder();
        String dbName = "test_base";
        DatabaseController databaseController = new DerbyDataBaseController(dbName);
        orderRepository = new AutoOrderRepository(databaseController);
        autoOrder = new AutoOrder();
        autoOrder.setId(1);
        autoOrder.setDate(new ConsoleDataFormat().dateStringToDate("22-10-2018"));
        autoOrder.setOwnerName("Alex");
        ordersList = new ArrayList<>();
        ordersList.add(autoOrder);

    }

    @Test
    public void tableCanBeCreated() throws SQLException {
        orderRepository.createTable(autoOrder);
        Assert.assertTrue(orderRepository.isTableExists(autoOrder));
        orderRepository.deleteTable(autoOrder);
    }

    @Test
    public void tableCanBeDeleted() throws SQLException {
        orderRepository.createTable(autoOrder);
        orderRepository.deleteTable(autoOrder);
        Assert.assertFalse(orderRepository.isTableExists(autoOrder));
    }

    @Test
    public void orderCanBeAdded() throws SQLException, IllegalAccessException {
        orderRepository.createTable(autoOrder);
        orderRepository.add(autoOrder);
        List<AutoOrder> orders = orderRepository.findAll();
        assertThat(autoOrder, is(orders.get(0)));
    }

    @Test
    public void allOrdersCanBeFound() throws SQLException, IllegalAccessException {
        orderRepository.createTable(autoOrder);
        orderRepository.add(autoOrder);
        ordersList = orderRepository.findAll();
        assertThat(autoOrder, is(ordersList.get(0)));
    }
}