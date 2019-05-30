package com.epam.console;

import static org.apache.log4j.LogManager.getLogger;

import com.epam.service.DataProvider;
import org.apache.log4j.Logger;

import java.util.Date;

public class ConsoleDataProvider implements DataProvider {

    Logger logger = getLogger(ConsoleDataProvider.class);
    private final ConsoleDataFormat consoleDataFormat = new ConsoleDataFormat();
    private final IConsoleManager consoleManager;

    public ConsoleDataProvider(IConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    @Override
    public Date getStartDate() {
        logger.info("Input start date: ");
        return consoleDataFormat.dateStringToDate(consoleManager.readNextLine());
    }

    @Override
    public Date getEndDate() {
        logger.info("Input end date: ");
        return consoleDataFormat.dateStringToDate(consoleManager.readNextLine());
    }
}
