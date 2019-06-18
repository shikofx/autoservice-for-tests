package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

//    public static final Logger logger = LogManager.getLogger(WebApplication.class);

    public static void main(String[] args) {
//        logger.info(">>> Run application");
        SpringApplication.run(WebApplication.class, args);
    }

}
