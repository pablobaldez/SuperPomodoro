package pablobaldez.github.superpomodoro.data;

import java.util.List;

import pablobaldez.github.superpomodoro.domain.workers.DataSource;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class RealmPomodoroDataSource implements DataSource<Pomodoro> {
    @Override
    public List<Pomodoro> findAll() {
        return null;
    }



    @Override
    public void save(Pomodoro pomodoro) {

    }
}
