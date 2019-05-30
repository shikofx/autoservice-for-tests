package com.epam.ws.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AutoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "owner_name")
    private String ownerName;

    public AutoOrder() {
    }

    public AutoOrder(String orderDate, String ownerName) {
        this.orderDate = orderDate;
        this.ownerName = ownerName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "AutoOrder{" +
               "orderId=" + orderId +
               ", orderDate=" + orderDate +
               ", ownerName='" + ownerName + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutoOrder autoOrder = (AutoOrder) o;
        return orderId.equals(autoOrder.orderId) &&
               orderDate.equals(autoOrder.orderDate) &&
               ownerName.equals(autoOrder.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, ownerName);
    }
}
