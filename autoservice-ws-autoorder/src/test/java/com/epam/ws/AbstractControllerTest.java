package com.epam.ws;

import com.epam.ws.model.AutoOrder;
import com.epam.ws.web.api.BaseController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractTest {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected void setUp(BaseController controller) {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    protected String mapToJson(AutoOrder obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json)
        throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, (Class<T>) AutoOrder.class);
    }
}
