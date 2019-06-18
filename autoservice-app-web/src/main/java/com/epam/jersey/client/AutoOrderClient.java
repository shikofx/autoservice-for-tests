package com.epam.jersey.client;

import static com.epam.jersey.config.AutoOrderRestConfig.AUTOORDER_SERVICE_PATH;
import static com.epam.jersey.config.AutoOrderRestConfig.CREATE_NEW_PATH;
import static com.epam.jersey.config.AutoOrderRestConfig.ORDER_ID_PATH;
import static com.epam.jersey.config.AutoOrderRestConfig.ORDER_ID_TEMPLATE;
import static javax.ws.rs.client.Entity.json;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static rp.org.apache.http.HttpStatus.SC_CREATED;
import static rp.org.apache.http.HttpStatus.SC_OK;

import com.epam.endpoints.AutoOrder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import rp.org.apache.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class AutoOrderClient {

    public static final String LOG_CLOSE_CLIENT_STRING = "<<< Close client!";
    private final ClientConfig config = new ClientConfig();
    private final Logger logger = LogManager.getLogger(AutoOrderClient.class);
    private Client client = ClientBuilder.newClient();

    public AutoOrder createNew(AutoOrder newOrder) {
        AutoOrder autoOrder = null;
        Response response;
        try {
            client = ClientBuilder.newClient();
            logger.info(">>> Create new auto order with: " + newOrder.toString());
            WebTarget add = client.target(AUTOORDER_SERVICE_PATH).path(CREATE_NEW_PATH);
            response = add.request(APPLICATION_JSON).post(json(newOrder));
            logger.debug(">>> >>> Response Http Status: " + response.getStatus());
            logger.info(response.getLocation());
            if (response.getStatus() == SC_CREATED) {
                logger.info("Order added successfully.");
                autoOrder = response.readEntity(AutoOrder.class);
            }
        } catch (Exception e) {
            logger.error(">>> >>> " + Arrays.toString(e.getStackTrace()));
        } finally {
            if (client != null) {
                logger.debug(LOG_CLOSE_CLIENT_STRING);
                client.close();
            }
        }

        return autoOrder;

    }

    public AutoOrder modify(AutoOrder order, AutoOrder withOrder) {
        AutoOrder autoOrder = null;
        Response response;
        try {
            client = ClientBuilder.newClient();
            logger.info(
                ">>> Modify new auto order by id: " + order.getOrderId() + " with order: " + withOrder.toString());
            WebTarget autoOrderById = client
                .target(AUTOORDER_SERVICE_PATH)
                .path(ORDER_ID_PATH)
                .resolveTemplate(ORDER_ID_TEMPLATE, order.getOrderId());
            response = autoOrderById
                .request(APPLICATION_JSON)
                .put(Entity.entity(withOrder, APPLICATION_JSON));
            logger.debug(">>> >>> Response Http Status: " + response.getStatus());
            if (response.getStatus() == SC_OK) {
                logger.info("Order modified successfully.");
                autoOrder = response.readEntity(AutoOrder.class);
                logger.info(">>> >>> Result is: " + autoOrder.toString());
            }
        } catch (Exception e) {
            logger.error(">>> " + Arrays.toString(e.getStackTrace()));
        } finally {
            if (client != null) {
                logger.debug(LOG_CLOSE_CLIENT_STRING);
                client.close();
            }
        }
        return autoOrder;

    }

    public List<AutoOrder> findAll() {
        List<AutoOrder> autoOrders = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(AUTOORDER_SERVICE_PATH);
            logger.info(">>> Find all orders.");
            autoOrders = target
                .request(APPLICATION_JSON)
                .get(new GenericType<List<AutoOrder>>() {
                });
        } catch (Exception e) {
            logger.error(">>> " + Arrays.toString(e.getStackTrace()));

        } finally {
            if (client != null) {
                logger.debug(LOG_CLOSE_CLIENT_STRING);
                client.close();
            }
        }
        return autoOrders;
    }

    public AutoOrder findById(String id) {
        AutoOrder autoOrder = null;
        try {
            client = ClientBuilder.newClient();
            logger.info(">>> Find order by id: " + id);
            WebTarget autoOrderById = client.target(
                AUTOORDER_SERVICE_PATH).path(ORDER_ID_PATH).resolveTemplate(ORDER_ID_TEMPLATE, id);
            autoOrder = autoOrderById.request(APPLICATION_JSON).get(AutoOrder.class);
            logger.info(">>> >>> Result is: " + autoOrder.toString());
            logger.info("Order found successfully.");
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return autoOrder;
        } finally {
            if (client != null) {
                logger.debug(LOG_CLOSE_CLIENT_STRING);
                client.close();
            }
        }
        return autoOrder;
    }

    public boolean delete(AutoOrder order) {
        Response response = null;
        try {
            config.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
            client = ClientBuilder.newClient(config);
            WebTarget deleteById = client
                .target(AUTOORDER_SERVICE_PATH)
                .path(ORDER_ID_PATH)
                .resolveTemplate(ORDER_ID_TEMPLATE, order.getOrderId());
            response = deleteById.request(APPLICATION_JSON)
                .build("DELETE", Entity.entity(order, APPLICATION_JSON))
                .invoke();
            logger.debug("Response Http Status: " + response.getStatus());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        } finally {
            if (client != null) {
                logger.debug(LOG_CLOSE_CLIENT_STRING);
                client.close();
            }
        }
        if (response != null && response.getStatus() == HttpStatus.SC_NO_CONTENT) {
            logger.info("Order deleted successfully.");
            return true;
        } else {
            return false;
        }
    }
}
