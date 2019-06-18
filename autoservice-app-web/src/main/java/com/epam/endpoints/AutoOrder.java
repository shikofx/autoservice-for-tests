package com.epam.endpoints;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderId",
    "orderDate",
    "ownerName"
})
public class AutoOrder {

    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("orderDate")
    private String orderDate;
    @JsonProperty("ownerName")
    private String ownerName;

    @JsonProperty("orderId")
    public String getOrderId() {
        return orderId;
    }

    public AutoOrder() {
        this.orderId = "";
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @JsonProperty("orderDate")
    public String getOrderDate() {
        return orderDate;
    }

    public AutoOrder withOrderDate(String orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    @JsonProperty("ownerName")
    public String getOwnerName() {
        return ownerName;
    }

    public AutoOrder withOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AutoOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
            .append('[');
        sb.append("orderId");
        sb.append('=');
        sb.append(this.orderId);
        sb.append(',');
        sb.append("orderDate");
        sb.append('=');
        sb.append(((this.orderDate == null) ? "<null>" : this.orderDate));
        sb.append(',');
        sb.append("ownerName");
        sb.append('=');
        sb.append(((this.ownerName == null) ? "<null>" : this.ownerName));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
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
        return Objects.equals(orderDate, autoOrder.orderDate) &&
               Objects.equals(ownerName, autoOrder.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, ownerName);
    }

    public AutoOrder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
}
