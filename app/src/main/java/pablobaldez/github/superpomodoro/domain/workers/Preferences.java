package pablobaldez.github.superpomodoro.domain.workers;

import rx.Completable;
import rx.Single;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public interface Preferences<T> {

    Single<T> get(Single<T> defaultSingle);

    Completable save(Single<T> toSaveSingle);

    Completable incrementPomodoro();

}
