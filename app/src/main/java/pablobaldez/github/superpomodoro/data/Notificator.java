package pablobaldez.github.superpomodoro.data;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.presentation.MainActivity;

/**
 * @author Pablo
 * @since 08/09/2016
 */
public class Notificator {

    private final Context context;

    @Inject
    public Notificator(Context context) {
        this.context = context;
    }

    public void notifyUser() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(context.getString(R.string.pomodoro_finished))
                        .setContentIntent(buildPendingIntent())
                        .setVibrate(new long[] { 500, 500, 500, 500, 500});
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1, builder.build());
    }

    private PendingIntent buildPendingIntent() {
        Intent resultIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
    }
}
