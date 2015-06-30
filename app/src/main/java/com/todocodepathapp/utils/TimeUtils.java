package com.todocodepathapp.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Years;

import java.util.Date;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TimeUtils {

    private static final String TAG = TimeUtils.class.getSimpleName();

    public static String timeAgo(Date date) {
        DateTime d = new DateTime(date.getTime());
        return getString(d, new DateTime());
    }

    private static String getString(DateTime date, DateTime now) {
        if (toYears(date, now) != null) {
            return toYears(date, now);
        }
        if (toMonths(date, now) != null) {
            return toMonths(date, now);
        }
        if (toDays(date, now) != null) {
            return toDays(date, now);
        }
        if (toHours(date, now) != null) {
            return toHours(date, now);
        }
        return toMinutes(date, now);
    }

    private static String toYears(DateTime date, DateTime now) {
        int result = Years.yearsBetween(date, now).getYears();
        if (Math.abs(result) >= 1) {
            return Math.abs(result) == 1 ? (result == 1 ? "Last" : "Next") + " year" :
                    toReference(Math.abs(result), result, " years ");
        }
        return null;
    }

    private static String toMonths(DateTime date, DateTime now) {
        int result = Months.monthsBetween(date, now).getMonths();
        if (Math.abs(result) >= 1) {
            return Math.abs(result) == 1 ? (result == 1 ? "Last" : "Next") + " month" :
                    toReference(Math.abs(result), result, " months ");
        }
        return null;
    }

    private static String toDays(DateTime date, DateTime now) {
        int result = Days.daysBetween(date, now).getDays();
        if (Math.abs(result) >= 1) {
            return Math.abs(result) == 1 ? (result == 1 ? "Yesterday" : "Tomorrow") :
                    toReference(Math.abs(result), result, " days ");
        }
        return null;
    }

    private static String toHours(DateTime date, DateTime now) {
        int result = Hours.hoursBetween(date, now).getHours();
        if (Math.abs(result) >= 1) {
            return Math.abs(result) == 1 ? "1 hour " + (result == 1 ? "ago" : "from now") :
                    toReference(Math.abs(result), result, " hours ");
        }
        return null;
    }

    private static String toMinutes(DateTime date, DateTime now) {
        int minutes = Minutes.minutesBetween(date, now).getMinutes();
        return toReference(Math.abs(minutes), minutes, " minutes ");
    }

    private static String toReference(long prefix, long suffixValue, String suffix) {
        return prefix + suffix + (suffixValue > 0 ? "ago" : "from now");
    }

}
