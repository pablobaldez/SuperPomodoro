package pablobaldez.github.superpomodoro.data;

import android.content.Context;
import android.content.SharedPreferences;

import pablobaldez.github.superpomodoro.domain.workers.Preferences;
import rx.Completable;
import rx.Single;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class UserPreferences  implements Preferences<UserSettings> {

    private static final String PREFERENCE = "user_preference";
    private static final String POMODORO_DURATION = "POMODORO_DURATION";
    private static final String NORMAL_INTERVAL_DURATION = "NORMAL_INTERVAL_DURATION";
    private static final String LONG_INTERVAL_DURATION = "LONG_INTERVAL_DURATION";
    private static final String TIMES_TO_LONG_INTERVAL = "TIMES_TO_LONG_INTERVAL";

    private final SharedPreferences preferences;


    public UserPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public Single<UserSettings> get(Single<UserSettings> singelDefault) {
        return singelDefault.flatMap(defaults -> Single.just(getUserSettingsFunc(defaults)));
    }

    public UserSettings getUserSettingsFunc(UserSettings defaults) {
        UserSettings userSetting = new UserSettings();
        userSetting.setPomodoroDurationTime(get(POMODORO_DURATION, defaults.getPomodoroDurationTime()));
        userSetting.setLongIntervalDuration(get(NORMAL_INTERVAL_DURATION, defaults.getNormalIntervalDuration()));
        userSetting.setNormalIntervalDuration(get(LONG_INTERVAL_DURATION, defaults.getLongIntervalDuration()));
        userSetting.setTimesToLongInterval(get(TIMES_TO_LONG_INTERVAL, defaults.getTimesToLongInterval()));
        return userSetting;
    }

    @Override
    public Completable save(Single<UserSettings> single) {
        return single.flatMapCompletable(userSettings ->
                Completable.fromAction(() -> saveAction(userSettings)));
    }

    private void saveAction(UserSettings userSettings) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putLong(POMODORO_DURATION, userSettings.getPomodoroDurationTime());
        edit.putLong(NORMAL_INTERVAL_DURATION, userSettings.getNormalIntervalDuration());
        edit.putLong(LONG_INTERVAL_DURATION, userSettings.getLongIntervalDuration());
        edit.putInt(TIMES_TO_LONG_INTERVAL, userSettings.getTimesToLongInterval());
        edit.apply();
    }

    private long get(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    private int get(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }
}
