package com.epam.tests.orders;

import static com.epam.controller.AutoOrderController.AUTO_ORDER_CONTROLLER;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

import com.epam.categories.TestCategories.NegativeTests;
import com.epam.categories.TestCategories.PositiveTests;
import com.epam.endpoints.AutoOrder;
import com.epam.tests.TestBase;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteOrderTests extends TestBase {

    private final Logger log = LogManager.getLogger(DeleteOrderTests.class);
    private AutoOrder orderToServer;
    private AutoOrder addedOrder;

    @Before
    public void setUp() {
        log.info("-->> setUp for " + DeleteOrderTests.class.getSimpleName());
        orderToServer = new AutoOrder()
            .withOrderDate("22.12.2018")
            .withOwnerName(TestBase.TEST_OWNER_STRING);
        log.info("-->> add data to server " + orderToServer.asString());
        addedOrder = AUTO_ORDER_CONTROLLER.add(orderToServer)
            .as(AutoOrder.class);
    }

    @After
    public void tearDown() {
        log.info("<<-- tearDown for " + DeleteOrderTests.class.getSimpleName());
    }

    @Test
    @Category(PositiveTests.class)
    public void deleteOrderSucceed() {
        log.info(">>> @Test deleteOrderSucceed of " + addedOrder.asString());
        log.info(">>> >> delete data");
        Response deleteResponse = AUTO_ORDER_CONTROLLER.delete(addedOrder);
        log.info(">>> >> find data");
        Response findByIdResponse = AUTO_ORDER_CONTROLLER.findById(addedOrder.getOrderId());
        log.info(">>> >> assertions");
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(deleteResponse.statusCode()).isEqualTo(SC_NO_CONTENT);
        assertions.assertThat(findByIdResponse.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertions.assertAll();
        log.info("<<< @Test deleteOrderSucceed");
    }

    @Test
    @Category(NegativeTests.class)
    public void deleteOrderWithZeroIdFailed() {
        addedOrder.withId(0);
        log.info(">>> @Test deleteOrderWithZeroIdFailed of " + addedOrder.asString());
        log.info(">>> >> delete data");
        Response deleteResponse = AUTO_ORDER_CONTROLLER.delete(orderToServer);
        log.info(">>> >> assertions");
        deleteResponse.then().statusCode(SC_INTERNAL_SERVER_ERROR);
        log.info("<<< @Test deleteOrderWithZeroIdFailed");
    }
}
