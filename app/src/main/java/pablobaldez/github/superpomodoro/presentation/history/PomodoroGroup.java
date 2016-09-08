package pablobaldez.github.superpomodoro.presentation.history;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pablobaldez.github.superpomodoro.domain.Pomodoro;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class PomodoroGroup {

    private Date day;
    private List<Pomodoro> pomodoros = new ArrayList<>();

    public PomodoroGroup(Pomodoro pomodoro) {
        this.day = pomodoro.getTook();
        add(pomodoro);
    }

    public Date getDay() {
        return day;
    }

    public void add(Pomodoro pomodoro) {
        pomodoros.add(pomodoro);
    }

    public int size() {
        if(pomodoros.isEmpty()){
            return 0;
        }
        return pomodoros.size() + 1;
    }
}
