package com.todocodepathapp.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class DateUtils {

    private static final String TAG = DateUtils.class.getSimpleName();

    public static Date parseDate(String time) {
        if (time != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            try {
                return simpleDateFormat.parse(time);
            } catch (ParseException ex) {
                Log.d(TAG, ex.getMessage());
            }
        }
        return null;
    }

    public static String dateToString(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            return simpleDateFormat.format(date);
        }
        return null;
    }

}
