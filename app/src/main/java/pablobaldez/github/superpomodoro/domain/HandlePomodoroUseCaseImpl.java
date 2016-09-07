package pablobaldez.github.superpomodoro.domain;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.data.ObservableCountDown;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import rx.Completable;
import rx.Observable;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class HandlePomodoroUseCaseImpl implements HandlePomodoroUseCase{

    private static final UserSettings DEFAULTS = new UserSettings(
            TimeUnit.MINUTES.toMillis(25),
            TimeUnit.MINUTES.toMillis(5),
            TimeUnit.MINUTES.toMillis(15),
            4
    );

    private final ObservableCountDown counter;
    private final DataSource<Pomodoro> dataSource;

    private Observable<Long> runningObservable;
    private Pomodoro currentPomodoro;

    @Inject
    public HandlePomodoroUseCaseImpl(ObservableCountDown counter, DataSource<Pomodoro> dataSource) {
        this.counter = counter;
        this.dataSource = dataSource;
    }

    @Override
    public Observable<Long> start() {
        currentPomodoro = new Pomodoro();
        currentPomodoro.setTook(new Date());
        runningObservable = counter.start(DEFAULTS.getPomodoroDurationTime())
                .doOnNext(currentPomodoro::incrementWorkedTime)
                .doOnCompleted(this::savePomodoroAction)
                .doOnUnsubscribe(this::savePomodoroAction);
        return runningObservable;
    }

    @Override
    public Completable stop() {
        if(runningObservable == null || currentPomodoro == null) {
            throw new IllegalStateException("You have to start before to stop");
        }
        return dataSource.save(currentPomodoro);
    }

    private void savePomodoroAction() {
        dataSource.save(currentPomodoro)
                .subscribeOn(Schedulers.io())
                .subscribe(Subscribers.empty());
    }
}
