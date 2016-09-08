package pablobaldez.github.superpomodoro.domain;

import java.util.concurrent.TimeUnit;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class UserSettings {

    public static final UserSettings DEFAULTS = new UserSettings(
            TimeUnit.MINUTES.toMillis(25),
            TimeUnit.MINUTES.toMillis(5),
            TimeUnit.MINUTES.toMillis(5),
            4
    );

    private long pomodoroDurationTime;
    private long normalIntervalDuration;
    private long longIntervalDuration;
    private int timesToLongInterval;
    private int currentPomodorosRuns;

    public UserSettings(long pomodoroDurationTime, long normalIntervalDuration, long longIntervalDuration, int timesToLongInterval) {
        this.pomodoroDurationTime = pomodoroDurationTime;
        this.normalIntervalDuration = normalIntervalDuration;
        this.longIntervalDuration = longIntervalDuration;
        this.timesToLongInterval = timesToLongInterval;
    }

    public UserSettings() {}

    public long getPomodoroDurationTime() {
        return pomodoroDurationTime;
    }

    public void setPomodoroDurationTime(long pomodoroDurationTime) {
        this.pomodoroDurationTime = pomodoroDurationTime;
    }

    public long getNormalIntervalDuration() {
        return normalIntervalDuration;
    }

    public void setNormalIntervalDuration(long normalIntervalDuration) {
        this.normalIntervalDuration = normalIntervalDuration;
    }

    public long getLongIntervalDuration() {
        return longIntervalDuration;
    }

    public void setLongIntervalDuration(long longIntervalDuration) {
        this.longIntervalDuration = longIntervalDuration;
    }

    public int getTimesToLongInterval() {
        return timesToLongInterval;
    }

    public void setTimesToLongInterval(int timesToLongInterval) {
        this.timesToLongInterval = timesToLongInterval;
    }

    public int getCurrentPomodorosRuns() {
        return currentPomodorosRuns;
    }

    public void setCurrentPomodorosRuns(int currentPomodorosRuns) {
        this.currentPomodorosRuns = currentPomodorosRuns;
    }
}