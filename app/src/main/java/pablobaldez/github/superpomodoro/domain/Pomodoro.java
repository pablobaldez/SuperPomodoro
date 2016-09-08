package pablobaldez.github.superpomodoro.domain;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class Pomodoro extends RealmObject{

    public static final String PRIMARY_KEY = "primaryKey";
    public static final String TOOK = "took";

    @PrimaryKey
    private long primaryKey;

    private Date took;
    private long workedTime = 0;
    private long endTime;
    private long definedDuration;
    private boolean finished;

    public long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setTook(Date took) {
        this.took = took;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void incrementWorkedTime(long millis) {
        workedTime += millis;
    }

    public void finish() {
        endTime = workedTime - definedDuration;
        workedTime = definedDuration;
        finished = endTime == 0;
    }

    public void setDefinedDuration(long definedDuration) {
        this.definedDuration = definedDuration;
    }

    public Date getTook() {
        return took;
    }

    public long getWorkedTime() {
        return workedTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getDefinedDuration() {
        return definedDuration;
    }

    public boolean isFinished() {
        return finished;
    }
}
