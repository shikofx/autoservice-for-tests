package com.epam.ws.web.api;

import com.epam.ws.model.AutoOrder;
import com.epam.ws.service.AutoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
class AutoOrderController extends BaseController {

    @Autowired
    private AutoOrderService autoOrderService;

    @GetMapping(value = "api/autoOrders",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AutoOrder>> getAutoOrders() {

        Collection<AutoOrder> autoOrders = autoOrderService.findAll();
        return new ResponseEntity<>(autoOrders, HttpStatus.OK);
    }

    @GetMapping(value = "api/autoOrders/{order_id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutoOrder> getAutoOrder(
        @PathVariable("order_id")
            Long orderId) {

        AutoOrder autoOrder = autoOrderService.findOne(orderId);
        if (autoOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(autoOrder, HttpStatus.OK);
    }

    @PostMapping(value = "api/autoOrders",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutoOrder> createAutoOrder(
        @RequestBody
            AutoOrder autoOrder) {

        autoOrderService.create(autoOrder);
        return new ResponseEntity<>(autoOrder, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/autoOrders/{order_id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutoOrder> updateAutoOrder(
        @RequestBody
            AutoOrder autoOrder) {

        AutoOrder updatedAutoOrder = autoOrderService.update(autoOrder);
        if (updatedAutoOrder == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedAutoOrder, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/autoOrders/{order_id}",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutoOrder> deleteAutoOrder(
        @PathVariable("order_id")
            Long orderId) {

        autoOrderService.delete(orderId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
