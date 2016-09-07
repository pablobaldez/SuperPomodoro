package pablobaldez.github.superpomodoro.data;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class UserSettings {

    private long pomodoroDurationTime;
    private long normalIntervalDuration;
    private long longIntervalDuration;
    private int timesToLongInterval;

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

    void setPomodoroDurationTime(long pomodoroDurationTime) {
        this.pomodoroDurationTime = pomodoroDurationTime;
    }

    public long getNormalIntervalDuration() {
        return normalIntervalDuration;
    }

    void setNormalIntervalDuration(long normalIntervalDuration) {
        this.normalIntervalDuration = normalIntervalDuration;
    }

    public long getLongIntervalDuration() {
        return longIntervalDuration;
    }

    void setLongIntervalDuration(long longIntervalDuration) {
        this.longIntervalDuration = longIntervalDuration;
    }

    public int getTimesToLongInterval() {
        return timesToLongInterval;
    }

    void setTimesToLongInterval(int timesToLongInterval) {
        this.timesToLongInterval = timesToLongInterval;
    }
}