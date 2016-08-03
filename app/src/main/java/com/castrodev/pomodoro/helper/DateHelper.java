package com.castrodev.pomodoro.helper;

import android.content.Context;

import com.castrodev.pomodoro.R;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rodrigocastro on 02/08/16.
 */
public class DateHelper {

    private static final int ONE_MINUTE_IN_MILLIS = 60 * 1000;
    private static final int ONE_HOUR_IN_MILLIS = 60 * ONE_MINUTE_IN_MILLIS;
    private static final int THREE_HOURS_IN_MILLIS = 3 * ONE_HOUR_IN_MILLIS;
    private static final int NONE = 0;
    private static final int ONE_DAY = 1;
    private static final String PATTERN = "dd/MM/yy";

    public static String getRelativeTimeText(Context context, Date date) {
        Duration duration = new Duration(date.getTime(), DateTime.now().getMillis());
        String relativeTime = "";
        if(duration.getMillis()<ONE_MINUTE_IN_MILLIS)
            relativeTime = context.getResources().getQuantityString(R.plurals.seconds, ((int) duration.getStandardSeconds()), duration.getStandardSeconds());
        else if(duration.getMillis()<ONE_HOUR_IN_MILLIS)
            relativeTime = context.getResources().getQuantityString(R.plurals.minutes, ((int) duration.getStandardMinutes()), duration.getStandardMinutes());
        else if(duration.getMillis()<THREE_HOURS_IN_MILLIS)
            relativeTime = context.getResources().getQuantityString(R.plurals.hours, ((int) duration.getStandardHours()), duration.getStandardHours());
        else{
            DateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.format_hour_am_pm), Locale.getDefault());
            relativeTime = dateFormat.format(date);
        }
        return relativeTime;
    }

    public static String getRelativeDateText(Date date) {

        DateTime datetime = new DateTime(date);
        DateTime now = DateTime.now();

        int daysBetweenDates = Days.daysBetween(datetime, now).getDays();
        String relativeDateText = "";

        if(daysBetweenDates == NONE){
            relativeDateText = "Today";
        } else if(daysBetweenDates == ONE_DAY){
            relativeDateText = "Yesterday";
        } else {
            relativeDateText = getFormattedDate(date);
        }

        return relativeDateText;
    }

    private static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
