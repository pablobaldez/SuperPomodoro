package pablobaldez.github.superpomodoro.presentation.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
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
            = new SimpleDateFormat("hh", Locale.getDefault());

    private TimeFormatUtils(){}

    public static String mmSS(long millis) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    public static String getHoursDiff(Context context, Date date) {
        long millis = date.getTime();
        long millisInOneHour = TimeUnit.HOURS.toMillis(1);
        long millisInOneMinute = TimeUnit.MINUTES.toMillis(1);

        if ( millis < millisInOneMinute) {
            return context.getString(R.string.now);
        }
        else if(millis < 2 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 1);
        }
        else if(millis < 6 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 5);
        }
        else if(millis < 11 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 10);
        }
        else if(millis < 16 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 15);
        }
        else if(millis < 31 * millisInOneMinute) {
            return String.format(Locale.getDefault(), context.getString(R.string.m_ago), 30);
        }
        //hour
        else if (millis < 2 * millisInOneHour) {
            return String.format(Locale.getDefault(), context.getString(R.string.h_ago), 1);
        }
        else if (millis < 6 * millisInOneHour) {
            return String.format(Locale.getDefault(), context.getString(R.string.h_ago), 5);
        }

        else {
            return HOUR_FORMAT.format(new Date(millis));
        }
    }

    public static String getDaysDiff(Context context, Date date) {
        long millis = date.getTime();
        long millisInOneDay = TimeUnit.DAYS.toMillis(1);
        int maxDaysAgo = 10;

        if ( millis < millisInOneDay) {
            return context.getString(R.string.today);
        }
        else if (millis < 2 * millisInOneDay) {
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

}
