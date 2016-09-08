package pablobaldez.github.superpomodoro.domain;

import rx.Observable;
import rx.Single;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public interface HandlePomodoroUseCase {

    Observable<Long> start();

    Single<Boolean> isTimeForInterval();

    Observable<Long> startInterval();

}
