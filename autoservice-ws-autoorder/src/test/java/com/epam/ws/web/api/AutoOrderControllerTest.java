package com.epam.ws.web.api;

import com.epam.ws.AbstractControllerTest;
import com.epam.ws.model.AutoOrder;
import com.epam.ws.service.AutoOrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AutoOrderControllerTest extends AbstractControllerTest {

    @Autowired
    private AutoOrderService autoOrderService;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetAutoOrders() throws Exception {

        String uri = "/api/autoOrders";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                                           .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status", 200, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);
    }

    @Test
    public void testGetAutoOrder() throws Exception {

        String uri = "/api/autoOrders/{id}";
        Long id = 1L;
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                                           .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);
    }

    @Test
    public void testGetAutoOrderNotFound() throws Exception {

        String uri = "/api/autoOrders/{id}";
        Long id = Long.MAX_VALUE;
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                                           .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
        Assert.assertEquals("failure - expected HTTP response body to be empty", 0, content.trim().length());

    }

    @Test
    public void testCreateAutoOrder() throws Exception {

        String uri = "/api/autoOrders";
        AutoOrder autoOrder = new AutoOrder();
        autoOrder.setOwnerName("test");
        autoOrder.setOrderDate("2019-05-17");
        String inputJson = super.mapToJson(autoOrder);

        MvcResult result = mvc
            .perform(MockMvcRequestBuilders.post(uri)
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON).content(inputJson))
            .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);
        AutoOrder createdAutoOrder = super.mapFromJson(content);
        Assert.assertNotNull("failure - expected autoOrder not null",
                             createdAutoOrder);
        Assert.assertNotNull("failure - expected autoOrder.id not null",
                             createdAutoOrder.getOrderId());
        Assert.assertEquals("failure - expected autoOrder.text match", "test",
                            createdAutoOrder.getOwnerName());

    }

    @Test
    public void testUpdateAutoOrder() throws Exception {

        String uri = "/api/autoOrders/{id}";
        Long id = 1L;
        AutoOrder autoOrder = autoOrderService.findOne(id);
        String updatedOwnerName = autoOrder.getOwnerName() + " test";
        autoOrder.setOwnerName(updatedOwnerName);
        String inputJson = super.mapToJson(autoOrder);
        MvcResult result = mvc
            .perform(MockMvcRequestBuilders.put(uri, id)
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON).content(inputJson))
            .andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
            "failure - expected HTTP response body to have a value",
            content.trim().length() > 0);
        AutoOrder updatedAutoOrder = super.mapFromJson(content);
        Assert.assertNotNull("failure - expected autoOrder not null",
                             updatedAutoOrder);
        Assert.assertEquals("failure - expected autoOrder.id unchanged",
                            autoOrder.getOrderId(), updatedAutoOrder.getOrderId());
        Assert.assertEquals("failure - expected updated autoOrder text match",
                            updatedOwnerName, updatedAutoOrder.getOwnerName());

    }

    @Test
    public void testDeleteAutoOrder() throws Exception {

        String uri = "/api/autoOrders/{id}";
        Long id = 1L;
        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id)
                                           .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP response body to be empty", 0, content.trim().length());
    }
}
