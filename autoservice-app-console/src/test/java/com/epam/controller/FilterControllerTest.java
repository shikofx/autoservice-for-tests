package com.epam.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.service.DataProvider;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class FilterControllerTest {

    private DataProvider dataProvider;
    private FilterController filterController;

    @Before
    public void setUp() {
        dataProvider = mock(DataProvider.class);
        filterController = new FilterController(dataProvider);
    }

    @After
    public void tearDown() {
        filterController = null;
    }

    @Test
    public void startDateLessOrEqualsThanCurrentDate() {
        Date currentDate = new Date();
        Date startDate = getDateLessThan(currentDate);
        when(dataProvider.getStartDate()).thenReturn(currentDate).thenReturn(startDate);
        Assert.assertTrue(filterController.readAndVerifyStartDate(currentDate));
        Assert.assertTrue(filterController.readAndVerifyStartDate(currentDate));
    }

    @Test
    public void endDateLessOrEqualsThanCurrentDate() {
        Date currentDate = new Date();
        Date endDate = getDateLessThan(currentDate);
        when(dataProvider.getEndDate()).thenReturn(currentDate).thenReturn(endDate);
        Assert.assertTrue(filterController.readAndVerifyEndDate(currentDate));
    }

    @Test
    public void startDateLessThanEndDate() {
        Date currentDate = new Date();
        Date startDate = getDateLessThan(currentDate);
        when(dataProvider.getStartDate()).thenReturn(startDate);
        when(dataProvider.getEndDate()).thenReturn(currentDate);
        Assert.assertTrue(
            filterController.readAndVerifyStartDate(currentDate) && filterController.readAndVerifyEndDate(currentDate));
    }

    @Test
    public void startDateEqualToEndDate() {
        Date currentDate = new Date();
        when(dataProvider.getStartDate()).thenReturn(currentDate);
        when(dataProvider.getEndDate()).thenReturn(currentDate);
        Assert.assertTrue(
            filterController.readAndVerifyStartDate(currentDate) && filterController.readAndVerifyEndDate(currentDate));
    }

    @Test
    public void failWhenStartDateGraterThanEndDate() {
        Date startDate = new Date();
        Date endDate = getDateLessThan(startDate);
        when(dataProvider.getStartDate()).thenReturn(startDate);
        when(dataProvider.getEndDate()).thenReturn(endDate);
        Assertions.assertThat(filterController.readAndVerifyStartDate(startDate)).isTrue();
    }


    @Test
    public void failWhenStartDateGraterThanCurrentDate() {
        Date currentDate = new Date();
        Date startDate = getDateGreaterThan(currentDate);
        when(dataProvider.getStartDate()).thenReturn(startDate);
        Assertions.assertThat(filterController.readAndVerifyStartDate(currentDate)).isFalse();
    }

    @Test
    public void failWhenEndDateGraterThanCurrentDate() {
        Date currentDate = new Date();
        Date endDate = getDateGreaterThan(currentDate);
        when(dataProvider.getEndDate()).thenReturn(endDate);
        Assertions.assertThat(filterController.readAndVerifyEndDate(currentDate)).isFalse();
    }

    private Date getDateLessThan(Date currentDate) {
        return new Date(currentDate.getTime() - 100000);
    }

    private Date getDateGreaterThan(Date currentDate) {
        return new Date(currentDate.getTime() + 100000);
    }
}
