package pablobaldez.github.superpomodoro.data;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.domain.Pomodoro;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import rx.Completable;
import rx.Observable;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class RealmPomodoroDataSource implements DataSource<Pomodoro> {

    @Inject
    public RealmPomodoroDataSource(){}

    @Override
    public Observable<Pomodoro> findAll() {
        return Observable.empty();
    }

    @Override
    public Completable save(Pomodoro pomodoro) {
        return Completable.complete();
    }
}
