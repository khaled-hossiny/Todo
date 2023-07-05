package com.todo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {
    public static Date stringToDate(String strDate, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance().getTime();
    }

    public static String DateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
