package pablobaldez.github.superpomodoro.domain;

import java.util.Date;

import io.realm.RealmObject;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class Pomodoro extends RealmObject{

    private long primaryKey;

    private Date took;
    private long workedTime;
    private long endTime;
    private long definedDuration;
    private boolean finished;

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

    public void setDefinedDuration(long definedDuration) {
        this.definedDuration = definedDuration;
    }
}
