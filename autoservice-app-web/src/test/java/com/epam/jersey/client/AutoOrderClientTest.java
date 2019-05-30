package com.epam.jersey.client;

import com.epam.endpoints.AutoOrder;
import com.epam.jersey.config.AutoOrderRestConfig;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoOrderClientTest {

    private AutoOrder orderToAdd;
    private AutoOrder modifyWithOrder;

    @Before
    public void setUp() {
        String currentDateString = AutoOrderRestConfig.SIMPLE_DATE_FORMAT.format(new Date());
        orderToAdd = new AutoOrder()
            .withOrderDate(currentDateString)
            .withOwnerName("Test User");
        modifyWithOrder = new AutoOrder()
            .withOrderDate(currentDateString)
            .withOwnerName("Modified User");
    }

    private final AutoOrderClient client = new AutoOrderClient();

    @Test
    public void createNewTest() {
        AutoOrder createdOrder = client.createNew(orderToAdd);
        client.delete(createdOrder);
        Assertions.assertThat(orderToAdd).isEqualTo(createdOrder);

    }

    @Test
    public void modifyByIdTest() {
        AutoOrder createdOrder = client.createNew(orderToAdd);
        modifyWithOrder.withOrderId(createdOrder.getOrderId());
        AutoOrder orderAfterModify = client.modify(createdOrder, modifyWithOrder);
        client.delete(createdOrder);
        Assertions.assertThat(modifyWithOrder).isEqualTo(orderAfterModify);
    }

    @Test
    public void findAllTest() {
        AutoOrder createdOrder = client.createNew(orderToAdd);
        List<AutoOrder> orders = client.findAll();
        client.delete(createdOrder);
        Assertions.assertThat(orders.size()).isGreaterThan(0);
        Assertions.assertThat(createdOrder).isIn(orders);


    }

    @Test
    public void findByIdTest() {
        AutoOrder createdOrder = client.createNew(orderToAdd);
        AutoOrder foundOrder = client.findById(createdOrder.getOrderId());
        client.delete(createdOrder);
        Assertions.assertThat(foundOrder).isEqualTo(createdOrder);
    }

    @Test
    public void deleteTest() {
        AutoOrder createdOrder = client.createNew(orderToAdd);
        Assertions.assertThat(client.delete(createdOrder)).isTrue();
    }
}