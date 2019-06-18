package com.epam.tests.orders;

import static com.epam.controller.AutoOrderController.AUTO_ORDER_CONTROLLER;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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

public class FindByIdTests extends TestBase {

    private final Logger log = LogManager.getLogger(FindByIdTests.class);
    private AutoOrder addedOrder;

    @Before
    public void setUp() {
        log.info("-->> setUp for " + FindByIdTests.class.getSimpleName());
        AutoOrder orderToServer = new AutoOrder()
            .withOrderDate("22.12.2018")
            .withOwnerName(TestBase.TEST_OWNER_STRING);
        log.info("-->> add data to server: " + orderToServer.asString());
        Response addResponse = AUTO_ORDER_CONTROLLER.add(orderToServer);
        addedOrder = addResponse.as(AutoOrder.class);
    }

    @After
    public void tearDown() {
        log.info("<<-- tearDown for " + FindByIdTests.class.getSimpleName());
    }

    @Test
    @Category(PositiveTests.class)
    public void findByIdSucceed() {
        log.info(">>> @Test findByIdSucceed of " + addedOrder.asString());
        log.info(">>> >> find by id = " + addedOrder.getOrderId());
        Response findByIdResponse = AUTO_ORDER_CONTROLLER.findById(addedOrder.getOrderId());
        AutoOrder foundOrder = findByIdResponse.as(AutoOrder.class);
        log.info(">>> >> found " + addedOrder.asString());
        log.info(">>> >> assertions");
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(findByIdResponse.statusCode()).isEqualTo(SC_OK);
        assertions.assertThat(addedOrder.getOrderId()).isEqualTo(foundOrder.getOrderId());
        assertions.assertThat(addedOrder.getOrderDate()).isEqualTo(foundOrder.getOrderDate());
        assertions.assertThat(addedOrder.getOwnerName()).isEqualTo(foundOrder.getOwnerName());
        assertions.assertAll();
        log.info("<<< @Test findByIdSucceed");
    }

    @Test
    @Category(NegativeTests.class)
    public void findByIdWithZeroIdFailed() {
        addedOrder.withId(0);
        log.info(">>> @Test findByIdWithZeroIdFailed of " + addedOrder.asString());
        log.info(">>> >> find by id = " + addedOrder.getOrderId());
        Response findByIdResponse = AUTO_ORDER_CONTROLLER.findById(addedOrder.getOrderId());
        log.info(">>> >> assertions");
        assertThat(findByIdResponse.statusCode(), is(HttpStatus.SC_NOT_FOUND));
        log.info("<<< @Test findByIdWithZeroIdFailed");
    }

}
