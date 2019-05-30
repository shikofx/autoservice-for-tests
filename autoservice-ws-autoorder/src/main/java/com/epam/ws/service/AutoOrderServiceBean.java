package com.epam.ws.service;

import com.epam.ws.model.AutoOrder;
import com.epam.ws.repository.AutoOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AutoOrderServiceBean implements AutoOrderService {

    @Autowired
    private AutoOrderRepository autoOrderRepository;

    @Override
    public Collection<AutoOrder> findAll() {
        return autoOrderRepository.findAll();
    }

    @Override
    public AutoOrder findOne(Long orderId) {
        return autoOrderRepository.findOne(orderId);
    }

    @Override
    public AutoOrder create(AutoOrder autoOrder) {
        if (autoOrder.getOrderId() != null) {
//            Cannot create AutoOrder with specified ID value
            return null;
        }
        return autoOrderRepository.save(autoOrder);
    }

    @Override
    public AutoOrder update(AutoOrder autoOrder) {
        AutoOrder autoOrderPersisted = findOne(autoOrder.getOrderId());
        if (autoOrderPersisted == null) {
//            Cannot update AutoOrder that hasn't been persisted
            return null;
        }
        return autoOrderRepository.save(autoOrder);
    }

    @Override
    public void delete(Long orderId) {
        autoOrderRepository.delete(orderId);
    }
}
