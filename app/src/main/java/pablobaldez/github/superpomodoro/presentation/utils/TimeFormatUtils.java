package pablobaldez.github.superpomodoro.presentation.utils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public final class TimeFormatUtils {

    private TimeFormatUtils(){}

    public static String mmSS(long millis) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

}
