package com.example.cancerhelpmate.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateManager {
    public static final String DATE_FORMAT_NOW = "dd/MM/yyyy";

    public static String getTodayAsString(){
        Calendar cal = Calendar.getInstance();
        return parseDateToString(cal);
    }

    public static String dateToDayMonthString(String stringDate){
        Date date = null;
        try {
            date=new SimpleDateFormat(DATE_FORMAT_NOW).parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("EEE dd MMMM");
        return fullDateFormat.format(date);
    }

    public static String dateToDayMonthYearString(String stringDate){
        Date date = null;
        try {
            date=new SimpleDateFormat(DATE_FORMAT_NOW).parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return fullDateFormat.format(date);
    }

    public static int noOfDaysBetween(String date1, String date2){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(date1);
            endDate = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = endDate.getTime() - startDate.getTime();

        long daysInMilli = 1000 * 60 * 60 * 24;

        int elapsedDays = (int) (difference / daysInMilli);

        return elapsedDays;
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
