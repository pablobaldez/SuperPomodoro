package pablobaldez.github.superpomodoro.presentation.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import pablobaldez.github.superpomodoro.R;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public final class TimeFormatUtils {

    private static final SimpleDateFormat DAY_FORMAT
            = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
    private static final SimpleDateFormat HOUR_FORMAT
            = new SimpleDateFormat("hh a", Locale.getDefault());

    private TimeFormatUtils(){}

    public static String mmSS(long millis) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    public static String getHoursDiff(Context context, Date date) {
        long millis = System.currentTimeMillis() - date.getTime();
        long millisInOneHour = TimeUnit.HOURS.toMillis(1);
        long millisInOneMinute = TimeUnit.MINUTES.toMillis(1);

        if ( millis < 2 *millisInOneMinute) {
            return context.getString(R.string.now);
        }
        else if(millis < 3 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 1);
        }
        else if(millis < 7 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 5);
        }
        else if(millis < 13 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 10);
        }
        else if(millis < 17 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 15);
        }
        else if(millis < 32 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 30);
        }
        //hour
        else if (millis < 3 * millisInOneHour) {
            return String.format(Locale.getDefault(), context.getString(R.string.h_ago), 1);
        }
        else if (millis < 7 * millisInOneHour) {
            return String.format(Locale.getDefault(), context.getString(R.string.h_ago), 5);
        }

        else {
            return HOUR_FORMAT.format(new Date(millis));
        }
    }

    public static String getDaysDiff(Context context, Date date) {
        long millis = System.currentTimeMillis() - date.getTime();
        long millisInOneDay = TimeUnit.DAYS.toMillis(1);
        int maxDaysAgo = 10;

        if (isSameDay(new Date(), date)) {
            return context.getString(R.string.today);
        }
        else if (millis < millisInOneDay) {
            return context.getString(R.string.yesterday);
        }
        else if (millis < maxDaysAgo * millisInOneDay) {
            int days = (int) (millis / millisInOneDay);
            return String.format(Locale.getDefault(), context.getString(R.string.days_ago), days);
        }
        else {
            return DAY_FORMAT.format(date);
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

}
