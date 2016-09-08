package pablobaldez.github.superpomodoro.domain;

import rx.Observable;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public interface HandlePomodoroUseCase {

    Observable<Long> start();

    Observable<Long> startInterval();

}
