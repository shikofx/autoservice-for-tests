package com.epam;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class WebApplication {

    private static final Logger logger = LogManager.getLogger(WebApplication.class);

    public static void main(String[] args) {
        logger.info(">>> Run application");
        SpringApplication.run(WebApplication.class, args);
    }

}
