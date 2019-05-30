package com.epam.data;

import com.epam.tests.TestBase;
import org.junit.runners.Parameterized.Parameters;

class SimpleDataProvider {

    @Parameters
    public static Object[][] ordersWithEmptyFields() {
        return new Object[][]{
            {"", ""},
            {"", TestBase.TEST_OWNER_STRING},
            {TestBase.TEST_DATE_STRING, ""}
        };
    }

    @Parameters
    public static Object[][] ordersWithNullFields() {
        return new Object[][]{
            {null, null},
            {null, TestBase.TEST_OWNER_STRING},
            {TestBase.TEST_DATE_STRING, null}
        };
    }

    @Parameters
    public static Object[][] ordersForModify() {
        return new Object[][]{
            {TestBase.TEST_DATE_STRING, "Test Albert"},
            {TestBase.TEST_DATE_STRING, "Test Peter"},
            {TestBase.TEST_DATE_STRING, "Test +-*/!@#$%^^&*()"},
            {TestBase.TEST_DATE_STRING, "Test 1aA0654679"}
        };
    }
}
