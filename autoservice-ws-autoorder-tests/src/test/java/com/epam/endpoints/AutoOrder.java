
package com.epam.endpoints;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeId;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderId",
    "orderDate",
    "ownerName"
})
public class AutoOrder {

    @JsonTypeId
    private int orderId;
    @JsonProperty("orderDate")
    private String orderDate;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("orderId")
    public int getOrderId() {
        return orderId;
    }

    public AutoOrder withId(int id) {
        this.orderId = id;
        return this;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public AutoOrder withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public String asString() {
        return "AutoOrder{" +
               "orderId=" + orderId +
               ", orderDate='" + orderDate + '\'' +
               ", ownerName='" + ownerName + '\'' +
               ", additionalProperties=" + additionalProperties +
               '}';
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + this.orderId);
        result = result * 31 + this.additionalProperties.hashCode();
        result = ((result * 31) + ((this.ownerName == null) ? 0 : this.ownerName.hashCode()));
        result = ((result * 31) + ((this.orderDate == null) ? 0 : this.orderDate.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AutoOrder)) {
            return false;
        }
        AutoOrder rhs = ((AutoOrder) other);
        return (
            (((this.orderId == rhs.orderId) && (Objects.equals(this.additionalProperties, rhs.additionalProperties)))
             && (Objects
                     .equals(
                         this.ownerName,
                         rhs.ownerName)))
            && (Objects
                    .equals(
                        this.orderDate,
                        rhs.orderDate)));
    }

}
