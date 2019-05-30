package com.epam.controller;

import com.epam.service.DataProvider;
import org.apache.log4j.Logger;

import java.util.Date;

public class FilterController {

    private Date startDate = new Date((new Date()).getTime() - 5000);
    private Date endDate = new Date();
    private final DataProvider dataProvider;
    Logger logger = Logger.getLogger(FilterController.class);

    public FilterController(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }


    public boolean readAndVerifyStartDate(Date currentDate) {
        startDate = dataProvider.getStartDate();
        return verifyDates(currentDate);
    }

    public boolean readAndVerifyEndDate(Date currentDate) {
        endDate = dataProvider.getEndDate();
        return verifyDates(currentDate);
    }

    private boolean verifyDates(Date currentDate) {
        if (startDate.getTime() > currentDate.getTime()) {
            logger.info("Start filter date must be less or equal than current date");
            return false;
        }
        if (endDate.getTime() > currentDate.getTime()) {
            logger.info("End filter date must be less or equal than current date");
            return false;
        }
        if (startDate.getTime() > endDate.getTime()) {
            logger.info("Start filter date must be less or equal than end filter date");
            return false;
        }
        return true;
    }
}
