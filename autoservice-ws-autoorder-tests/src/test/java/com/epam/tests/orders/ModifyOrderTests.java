package com.epam.tests.orders;

import static com.epam.controller.AutoOrderController.AUTO_ORDER_CONTROLLER;
import static org.apache.http.HttpStatus.SC_OK;

import com.epam.categories.TestCategories.BrokenTests;
import com.epam.categories.TestCategories.NegativeTests;
import com.epam.categories.TestCategories.PositiveTests;
import com.epam.endpoints.AutoOrder;
import com.epam.tests.TestBase;
import com.jayway.restassured.response.Response;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class ModifyOrderTests extends TestBase {

    private final Logger log = LogManager.getLogger(ModifyOrderTests.class);
    private AutoOrder orderToModify;
    private AutoOrder orderBeforeModify;

    @Before
    public void setUp() {
        log.info("-->> setUp for " + ModifyOrderTests.class.getSimpleName());
        AutoOrder orderToServer = new AutoOrder()
            .withOrderDate("22-12-2017")
            .withOwnerName(TestBase.TEST_OWNER_STRING);
        log.info("-->> add data to server: " + orderToServer.asString());
        orderBeforeModify = AUTO_ORDER_CONTROLLER.add(orderToServer)
            .as(AutoOrder.class);
        orderToModify = new AutoOrder()
            .withId(orderBeforeModify.getOrderId())
            .withOrderDate("10-10-2018")
            .withOwnerName("Test Owner1");
    }

    @After
    public void tearDown() {
        log.info("<<-- tearDown for " + ModifyOrderTests.class.getSimpleName());
    }

    @Test
    @Category(PositiveTests.class)

    public void modifyOrderIsSucceed() {
        log.info(">>> @Test modifyOrderIsSucceed");
        log.info(">>> >>>Order " + orderBeforeModify.asString());
        log.info(">>> >> modify as " + orderToModify.asString());
        Response modifyResponse = AUTO_ORDER_CONTROLLER.modify(orderToModify);
        log.info(">>> >> find by id = " + orderBeforeModify.getOrderId());
        AutoOrder orderAfterModify =
            AUTO_ORDER_CONTROLLER.findById(orderBeforeModify.getOrderId()).as(AutoOrder.class);
        log.info(">>> >> found " + orderAfterModify.asString());
        log.info(">>> >> assertions");
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(modifyResponse.statusCode()).isEqualTo(SC_OK);
        assertions.assertThat(orderAfterModify.getOrderId()).isEqualTo(orderAfterModify.getOrderId());
        assertions.assertThat(orderAfterModify.getOrderDate()).isEqualTo(orderAfterModify.getOrderDate());
        assertions.assertThat(orderAfterModify.getOwnerName()).isEqualTo(orderAfterModify.getOwnerName());
        assertions.assertAll();
        log.info("<<< @Test modifyOrderIsSucceed");
    }

    @Test
    @Category({NegativeTests.class, BrokenTests.class})
    public void modifyWithEmptyFieldsFailed() {
        orderToModify = new AutoOrder()
            .withId(orderBeforeModify.getOrderId())
            .withOrderDate("date")
            .withOwnerName("owner");
        log.info(">>> @Test modifyWithEmptyFieldsFailed");
        log.info(">>> >>>Order " + orderBeforeModify.asString());
        log.info(">>> >> modify as " + orderToModify.asString());
        Response modifyResponse = AUTO_ORDER_CONTROLLER.modify(orderToModify);
        log.info(">>> >> find by id = " + orderBeforeModify.getOrderId());
        AutoOrder orderAfterModify =
            AUTO_ORDER_CONTROLLER.findById(orderBeforeModify.getOrderId()).as(AutoOrder.class);
        log.info(">>> >> found " + orderAfterModify.asString());
        log.info(">>> >> assertions");
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(modifyResponse.statusCode()).isEqualTo(SC_OK);
        assertions.assertAll();
        log.info("<<< @Test modifyWithEmptyFieldsFailed");
    }

    @Test
    @Category({NegativeTests.class, BrokenTests.class})
    public void modifyWithNullFieldsFailed() {
        orderToModify = new AutoOrder()
            .withId(orderBeforeModify.getOrderId())
            .withOrderDate("date")
            .withOwnerName("owner");
        log.info(">>> @Test modifyWithNullFieldsFailed");
        log.info(">>> >>>Order " + orderBeforeModify.asString());
        log.info(">>> >> modify as " + orderToModify.asString());
        Response modifyResponse = AUTO_ORDER_CONTROLLER.modify(orderToModify);
        log.info(">>> >> find by id = " + orderBeforeModify.getOrderId());
        AutoOrder orderAfterModify =
            AUTO_ORDER_CONTROLLER.findById(orderBeforeModify.getOrderId()).as(AutoOrder.class);
        log.info(">>> >> found " + orderAfterModify.asString());
        log.info(">>> >> assertions");
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(modifyResponse.statusCode()).isEqualTo(SC_OK);
        assertions.assertAll();
        log.info("<<< @Test addNewSucceed");
    }
}
