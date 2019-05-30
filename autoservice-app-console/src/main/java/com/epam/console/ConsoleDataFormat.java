package com.epam.console;

import com.epam.service.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleDataFormat implements DateFormat<String> {


    private static final String LEGAL_DEVIDERS = "[./]";
    private static final String COMMON_DEVIDER = "-";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String DATE_FORMAT_FULL = "EEE MMM dd HH:mm:ss zzz yyyy";

    @Override
    public Date dateStringToDate(String dateString) {
        String date = dateString.replaceAll(LEGAL_DEVIDERS, COMMON_DEVIDER);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Date fullDateStringToDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_FULL);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
