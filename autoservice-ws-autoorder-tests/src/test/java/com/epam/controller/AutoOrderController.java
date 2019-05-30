package com.epam.controller;

import static com.epam.config.AutoOrderConfig.CREATE_PATH;
import static com.epam.config.AutoOrderConfig.DELETE_PATH;
import static com.epam.config.AutoOrderConfig.FIND_ALL_PATH;
import static com.epam.config.AutoOrderConfig.MODIFY_PATH;
import static com.epam.config.AutoOrderConfig.ORDER_BASE_URL;
import static com.epam.config.AutoOrderConfig.ORDER_ID;
import static com.epam.config.AutoOrderConfig.ORDER_ID_PATH;

import com.epam.endpoints.AutoOrder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class AutoOrderController {

    public static final AutoOrderController AUTO_ORDER_CONTROLLER = new AutoOrderController();


    public Response findAll() {
        return this.given()
            .when()
            .get(FIND_ALL_PATH)
            .then()
            .extract().response();
    }

    public Response findById(int orderId) {
        return this.given()
            .pathParam(ORDER_ID, orderId)
            .when()
            .get(ORDER_ID_PATH)
            .then()
            .extract().response();
    }

    public Response add(AutoOrder newOrder) {
        return this.given()
            .body(newOrder)
            .when()
            .post(CREATE_PATH)
            .then().extract().response();
    }

    public Response modify(AutoOrder newOrder) {
        return this.given()
            .body(newOrder.withAdditionalProperty("orderId", newOrder.getOrderId()))
            .when()
            .put(MODIFY_PATH, newOrder.getOrderId())
            .then().extract().response();
    }

    public Response delete(AutoOrder order) {
        return this.given()
            .body(order)
            .when()
            .delete(DELETE_PATH, order.getOrderId())
            .then().extract().response();
    }

    private RequestSpecification given() {
        return RestAssured.given()
            .baseUri(ORDER_BASE_URL)
            .contentType(ContentType.JSON);
    }
}
