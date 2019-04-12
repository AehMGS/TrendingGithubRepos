package com.aeh.trendinggithubrepos.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String getFormattedDateOneMonthAgo(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date currentDate = calendar.getTime();

        return dateFormat.format(currentDate);
    }
}
