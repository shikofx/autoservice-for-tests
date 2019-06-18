package com.epam.service;

import java.util.Date;

public interface DateFormat<T> {

    Date dateStringToDate(T t);

    Date fullDateStringToDate(String dateString);
}
