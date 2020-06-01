package com.example.cancerhelpmate.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateManager {
    public static final String DATE_FORMAT_NOW = "dd/MM/yyyy";

    public static String getTodayAsString(){
        Calendar cal = Calendar.getInstance();
        return parseDateToString(cal);
    }

    public static String getYesterdayAsString(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

        return parseDateToString(cal);
    }

    public static String parseDateToString(Calendar calendar){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(calendar.getTime());
    }

    public static List<Integer> getYearMonthDay(String date){
        List<Integer> yearMonthDate = new ArrayList<>();

        Integer day = Integer.parseInt(date.substring(0,1));
        Integer month = Integer.parseInt(date.substring(2,3));
        Integer year = Integer.parseInt(date.substring(4,7));

        yearMonthDate.add(year);
        yearMonthDate.add(month);
        yearMonthDate.add(day);

        return yearMonthDate;
    }
}
