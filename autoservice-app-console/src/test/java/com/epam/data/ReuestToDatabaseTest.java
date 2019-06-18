package com.epam.data;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.epam.console.ConsoleDataFormat;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ReuestToDatabaseTest {

    private ConsoleDataFormat consoleDataFormat;

    @Before
    public void setUp() {
        consoleDataFormat = new ConsoleDataFormat();
    }

    @Test
    public void requestHaveStartDate() {
        Date startDate = consoleDataFormat.dateStringToDate("22/03/2019");
        RequestToFindOrders request = new RequestToFindOrders().
            withStartDate(startDate);
        assertThat(startDate, is(request.getStartDate()));
    }

    @Test
    public void requestHaveEndDate() {
        Date endDate = consoleDataFormat.dateStringToDate("22/03/2019");
        RequestToFindOrders request = new RequestToFindOrders().
            withEndDate(endDate);
        assertThat(endDate, is(request.getEndDate()));
    }
}
