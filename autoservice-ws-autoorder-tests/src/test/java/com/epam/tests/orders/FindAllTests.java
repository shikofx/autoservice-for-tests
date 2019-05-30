package com.epam.tests.orders;

import static com.epam.categories.TestCategories.PositiveTests;
import static com.epam.controller.AutoOrderController.AUTO_ORDER_CONTROLLER;
import static org.apache.http.HttpStatus.SC_OK;

import com.epam.endpoints.AutoOrder;
import com.epam.tests.TestBase;
import com.jayway.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FindAllTests extends TestBase {

    private final Logger log = LogManager.getLogger(FindAllTests.class);

    @Before
    public void setUp() {
        log.info("-->> setUp for " + FindAllTests.class.getSimpleName());
        AutoOrder orderToServer = new AutoOrder()
            .withOrderDate("22.12.2018")
            .withOwnerName(TestBase.TEST_OWNER_STRING);
        log.info("-->> add data to server: " + orderToServer.asString());
        AUTO_ORDER_CONTROLLER.add(orderToServer);
    }

    @After
    public void tearDown() {
        log.info("<<-- tearDown for " + FindAllTests.class.getSimpleName());
    }

    @Test
    @Category(PositiveTests.class)
    public void findAllSucceed() {
        log.info(">>> @Test findAllSucceed");
        log.info(">>> >> find all data");
        Response findAllResponse = AUTO_ORDER_CONTROLLER.findAll();
        log.info(">>> >> assertions");
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(findAllResponse.statusCode()).isEqualTo(SC_OK);
        assertions.assertThat(findAllResponse.body()).isNotNull();
        assertions.assertAll();
        log.info("<<< @Test findAllSucceed");
    }
}
