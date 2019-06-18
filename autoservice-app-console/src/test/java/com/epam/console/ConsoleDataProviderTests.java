package com.epam.console;

import static com.epam.console.ConsoleDataFormat.DATE_FORMAT;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleDataProviderTests {

    private SimpleDateFormat simpleDateFormat;
    private IConsoleManager consoleManager;
    private ConsoleDataProvider consoleDataProvider;

    @Before
    public void setUp() {
        simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        consoleManager = mock(IConsoleManager.class);
        consoleDataProvider = new ConsoleDataProvider(consoleManager);
    }

    @Test
    public void readStartDateFromConsole() throws ParseException {
        String dateString = "20-12-2019";
        Date date = simpleDateFormat.parse(dateString);
        when(consoleManager.readNextLine()).thenReturn(dateString);
        Assert.assertThat(date, is(consoleDataProvider.getStartDate()));
    }

    @Test
    public void readEndDateFromConsole() throws ParseException {
        String dateString = "20-12-2019";
        Date date = simpleDateFormat.parse(dateString);
        when(consoleManager.readNextLine()).thenReturn(dateString);
        Assert.assertThat(date, is(consoleDataProvider.getEndDate()));
    }
}
