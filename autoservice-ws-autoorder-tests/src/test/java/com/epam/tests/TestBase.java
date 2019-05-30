package com.epam.tests;

import static com.epam.controller.AutoOrderController.AUTO_ORDER_CONTROLLER;

import com.epam.endpoints.AutoOrder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TestBase {

    private static final Logger log = LogManager.getLogger(TestBase.class);
    public static final String TEST_DATE_STRING = "22-12-2019";
    public static final String TEST_OWNER_STRING = "Test Owner";

    @BeforeClass
    public static void init() {
        log.debug("--> init new instance");
    }

    @AfterClass
    public static void deinit() {
        log.debug("<-- deinit instance");
        log.debug("delete all test data from database");
        List<AutoOrder> orders = Arrays.asList(AUTO_ORDER_CONTROLLER.findAll().as(AutoOrder[].class))
            .stream().filter(autoOrder ->
                                 autoOrder.getOwnerName() == null ||
                                 autoOrder.getOrderDate() == null ||
                                 autoOrder.getOwnerName().contains("Test") ||
                                 autoOrder.getOwnerName().contains("test") ||
                                 "Owner".equals(autoOrder.getOwnerName()) ||
                                 "owner".equals(autoOrder.getOwnerName()) ||
                                 "".equals(autoOrder.getOwnerName())
            ).collect(Collectors.toList());
        orders.forEach(AUTO_ORDER_CONTROLLER::delete);
    }
}
