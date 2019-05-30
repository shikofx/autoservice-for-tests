package com.epam.ws.service;

import com.epam.ws.model.AutoOrder;

import java.util.Collection;

public interface AutoOrderService {

    Collection<AutoOrder> findAll();

    AutoOrder findOne(Long orderId);

    AutoOrder create(AutoOrder autoOrder);

    AutoOrder update(AutoOrder autoOrder);

    void delete(Long orderId);
}
