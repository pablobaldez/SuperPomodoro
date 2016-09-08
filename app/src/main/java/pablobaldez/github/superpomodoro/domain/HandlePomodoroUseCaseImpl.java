package pablobaldez.github.superpomodoro.domain;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.data.ObservableCountDown;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import pablobaldez.github.superpomodoro.domain.workers.Preferences;
import rx.Observable;
import rx.Single;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class HandlePomodoroUseCaseImpl implements HandlePomodoroUseCase{

    private static final UserSettings DEFAULTS = new UserSettings(
            TimeUnit.SECONDS.toMillis(3),
            TimeUnit.MINUTES.toMillis(5),
            TimeUnit.MINUTES.toMillis(15),
            4
    );

    private final ObservableCountDown counter;
    private final DataSource<Pomodoro> dataSource;
    private final Preferences<UserSettings> preferences;

    private Pomodoro currentPomodoro;

    @Inject
    public HandlePomodoroUseCaseImpl(ObservableCountDown counter,
                                     DataSource<Pomodoro> dataSource,
                                     Preferences<UserSettings> preferences) {
        this.counter = counter;
        this.dataSource = dataSource;
        this.preferences = preferences;
    }

    @Override
    public Observable<Long> start() {
        currentPomodoro = new Pomodoro();
        currentPomodoro.setTook(new Date());
        return preferences.get(Single.just(DEFAULTS))
                .flatMapObservable(userSettings ->
                        counter.start(userSettings.getPomodoroDurationTime())
                                .doOnNext(currentPomodoro::incrementWorkedTime)
                                .doOnUnsubscribe(this::savePomodoroAction)
                );
    }

    @Override
    public Single<Boolean> isTimeForInterval() {
        return null;
    }

    @Override
    public Observable<Long> startInterval() {
        return preferences.get(Single.just(DEFAULTS))
                .flatMapObservable(userSettings -> counter.start(userSettings.getNormalIntervalDuration()));
    }

    private void savePomodoroAction() {
        currentPomodoro.finish();
        dataSource.save(Single.just(currentPomodoro))
                .mergeWith(preferences.incrementPomodoro())
                .subscribeOn(Schedulers.io())
                .subscribe(Subscribers.empty());
    }
}
