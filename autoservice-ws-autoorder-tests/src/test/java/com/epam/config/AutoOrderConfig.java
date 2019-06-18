package com.epam.config;

public class AutoOrderConfig {

    public static final String ORDER_ID = "orderId";
    public static final String ORDER_BASE_URL = "http://localhost:8070";
    public static final String CREATE_PATH = "/api/autoOrders/";
    public static final String MODIFY_PATH = "/api/autoOrders/{" + ORDER_ID + "}";
    public static final String DELETE_PATH = "/api/autoOrders/{" + ORDER_ID + "}";
    public static final String ORDER_ID_PATH = "/api/autoOrders/{" + ORDER_ID + "}";


    public static final String FIND_ALL_PATH = "/api/autoOrders/";
}
