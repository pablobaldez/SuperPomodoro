package pablobaldez.github.superpomodoro.domain.workers;

import pablobaldez.github.superpomodoro.domain.Pomodoro;
import rx.Completable;
import rx.Observable;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public interface DataSource<T> {

    Observable<Pomodoro> findAll();

    Completable save(T pomodoro);

}
