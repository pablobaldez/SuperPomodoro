package pablobaldez.github.superpomodoro.data;

import java.util.Date;

import io.realm.RealmObject;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class Pomodoro extends RealmObject{

    private long primaryKey;

    private Date took;
    private long endTime;
    private boolean finished;

}
