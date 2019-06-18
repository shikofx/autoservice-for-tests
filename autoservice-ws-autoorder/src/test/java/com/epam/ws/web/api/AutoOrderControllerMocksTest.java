package com.epam.ws.web.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.ws.AbstractControllerTest;
import com.epam.ws.model.AutoOrder;
import com.epam.ws.service.AutoOrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Transactional
public class AutoOrderControllerMocksTest extends AbstractControllerTest {

    @Mock
    private AutoOrderService autoOrderService;

    /**
     * A AutoOrderController instance with <code>@Mock</code> com.epam.components injected into it.
     */
    @InjectMocks
    private AutoOrderController autoOrderController;

    /**
     * Setup each test method. Initialize Mockito mock and spy objects. Scan for Mockito annotations.
     */
    @Before
    public void setUp() {
        // Initialize Mockito annotated com.epam.components
        MockitoAnnotations.initMocks(this);
        // Prepare the Spring MVC Mock com.epam.components for standalone testing
        setUp(autoOrderController);
    }

    @Test
    public void testGetAutoOrders() throws Exception {

        // Create some test data
        Collection<AutoOrder> list = getEntityListStubData();

        // Stub the AutoOrderService.findAll method return value
        when(autoOrderService.findAll()).thenReturn(list);

        // Perform the behavior being tested
        String uri = "/api/autoOrders";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                                           .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the AutoOrderService.findAll method was invoked once
        verify(autoOrderService, times(1)).findAll();

        // Perform standard JUnit assertions on the response
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);

    }

    @Test
    public void testGetAutoOrder() throws Exception {

        // Create some test data
        Long id = 1L;
        AutoOrder entity = getEntityStubData();

        // Stub the AutoOrderService.findOne method return value
        when(autoOrderService.findOne(id)).thenReturn(entity);

        // Perform the behavior being tested
        String uri = "/api/autoOrders/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                                           .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the AutoOrderService.findOne method was invoked once
        verify(autoOrderService, times(1)).findOne(id);

        // Perform standard JUnit assertions on the test results
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);
    }

    @Test
    public void testGetAutoOrderNotFound() throws Exception {

        // Create some test data
        Long id = Long.MAX_VALUE;

        // Stub the AutoOrderService.findOne method return value
        when(autoOrderService.findOne(id)).thenReturn(null);

        // Perform the behavior being tested
        String uri = "/api/autoOrders/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                                           .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the AutoOrderService.findOne method was invoked once
        verify(autoOrderService, times(1)).findOne(id);

        // Perform standard JUnit assertions on the test results
        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
        Assert.assertEquals("failure - expected HTTP response body to be empty", 0, content.trim().length());

    }

    @Test
    public void testCreateAutoOrder() throws Exception {

        // Create some test data
        AutoOrder entity = getEntityStubData();

        // Stub the AutoOrderService.create method return value
        when(autoOrderService.create(any(AutoOrder.class))).thenReturn(entity);

        // Perform the behavior being tested
        String uri = "/api/autoOrders";
        String inputJson = super.mapToJson(entity);

        MvcResult result = mvc
            .perform(MockMvcRequestBuilders.post(uri)
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON).content(inputJson))
            .andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the AutoOrderService.create method was invoked once
        verify(autoOrderService, times(1)).create(any(AutoOrder.class));

        // Perform standard JUnit assertions on the test results
        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);

        AutoOrder createdEntity = super.mapFromJson(content);

        Assert.assertNotNull("failure - expected entity not null",
                             createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null",
                             createdEntity.getOrderId());
        Assert.assertEquals("failure - expected text attribute match",
                            entity.getOwnerName(), createdEntity.getOwnerName());
    }

    @Test
    public void testUpdateAutoOrder() throws Exception {

        // Create some test data
        AutoOrder entity = getEntityStubData();
        entity.setOwnerName(entity.getOwnerName() + " test");
        Long id = 1L;

        // Stub the AutoOrderService.update method return value
        when(autoOrderService.update(any(AutoOrder.class))).thenReturn(entity);

        // Perform the behavior being tested
        String uri = "/api/autoOrders/{id}";
        String inputJson = super.mapToJson(entity);

        MvcResult result = mvc
            .perform(MockMvcRequestBuilders.put(uri, id)
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON).content(inputJson))
            .andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the AutoOrderService.update method was invoked once
        verify(autoOrderService, times(1)).update(any(AutoOrder.class));

        // Perform standard JUnit assertions on the test results
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);

        AutoOrder updatedEntity = super.mapFromJson(content);
        Assert.assertNotNull("failure - expected entity not null",
                             updatedEntity);
        Assert.assertEquals("failure - expected id attribute unchanged",
                            entity.getOrderId(), updatedEntity.getOrderId());
        Assert.assertEquals("failure - expected text attribute match",
                            entity.getOwnerName(), updatedEntity.getOwnerName());
    }

    private Collection<AutoOrder> getEntityListStubData() {
        Collection<AutoOrder> list = new ArrayList<>();
        list.add(getEntityStubData());
        return list;
    }

    private AutoOrder getEntityStubData() {
        AutoOrder entity = new AutoOrder();
        entity.setOrderId(1L);
        entity.setOwnerName("hello");
        entity.setOrderDate("world");
        return entity;
    }
}
