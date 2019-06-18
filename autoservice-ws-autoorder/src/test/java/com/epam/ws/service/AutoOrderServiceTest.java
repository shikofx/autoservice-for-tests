package com.epam.ws.service;

import com.epam.ws.AbstractTest;
import com.epam.ws.model.AutoOrder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class AutoOrderServiceTest extends AbstractTest {

    @Autowired
    private AutoOrderService service;

    @Test
    public void testFindAll() {

        Collection<AutoOrder> listAutoOrders = service.findAll();
        Assert.assertNotNull("failure - expected not null", listAutoOrders);
    }

    @Test
    public void testFindOne() {

        Long id = 1L;
        AutoOrder entity = service.findOne(id);
        Assert.assertNotNull("failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", id, entity.getOrderId());
    }

    @Test
    public void testFindOneNotFound() {

        Long id = Long.MAX_VALUE;
        AutoOrder entity = service.findOne(id);
        Assert.assertNull("failure - expected null", entity);
    }

    @Test
    public void testCreate() {

        AutoOrder entity = new AutoOrder();
        entity.setOwnerName("test");
        entity.setOrderDate("2019-06-15");
        AutoOrder createdEntity = service.create(entity);
        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getOrderId());
    }

    @Test
    public void testUpdate() {

        Long id = 1L;
        AutoOrder entity = service.findOne(id);
        Assert.assertNotNull("failure - expected not null", entity);
        String updatedOwnerName = entity.getOwnerName() + " test";
        entity.setOwnerName(updatedOwnerName);
        AutoOrder updatedEntity = service.update(entity);
        Assert.assertNotNull("failure - expected not null", updatedEntity);
        Assert.assertEquals("failure - expected id attribute match", id,
                            updatedEntity.getOrderId());
        Assert.assertEquals("failure - expected text attribute match",
                            updatedOwnerName, updatedEntity.getOwnerName());

    }

    @Test
    public void testDelete() {

        Long id = 1L;
        AutoOrder entity = service.findOne(id);
        Assert.assertNotNull("failure - expected not null", entity);
        service.delete(id);
        AutoOrder deletedEntity = service.findOne(id);
        Assert.assertNull("failure - expected null", deletedEntity);
    }
}
