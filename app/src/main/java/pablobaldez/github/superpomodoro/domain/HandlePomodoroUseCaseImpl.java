package pablobaldez.github.superpomodoro.domain;


import java.util.Date;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.data.Notificator;
import pablobaldez.github.superpomodoro.data.ObservableCountDown;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import pablobaldez.github.superpomodoro.domain.workers.Preferences;
import rx.Observable;
import rx.Single;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import static pablobaldez.github.superpomodoro.domain.UserSettings.*;
/**
 * @author Pablo
 * @since 07/09/2016
 */
public class HandlePomodoroUseCaseImpl implements HandlePomodoroUseCase{

    private final ObservableCountDown counter;
    private final DataSource<Pomodoro> dataSource;
    private final Preferences<UserSettings> preferences;
    private final Notificator notificator;

    @Inject
    public HandlePomodoroUseCaseImpl(ObservableCountDown counter,
                                     DataSource<Pomodoro> dataSource,
                                     Preferences<UserSettings> preferences,
                                     Notificator notificator) {
        this.counter = counter;
        this.dataSource = dataSource;
        this.preferences = preferences;
        this.notificator = notificator;
    }

    @Override
    public Observable<Long> start() {
        Pomodoro currentPomodoro = new Pomodoro();
        currentPomodoro.setTook(new Date());
        return preferences.get(Single.just(DEFAULTS))
                .flatMapObservable(userSettings -> {
                    currentPomodoro.setDefinedDuration(userSettings.getPomodoroDurationTime());
                    return counter.start(userSettings.getPomodoroDurationTime())
                            .doOnNext(currentPomodoro::setEndTime)
                            .doOnCompleted(notificator::notifyUser)
                            .doOnUnsubscribe(() -> this.savePomodoroAction(currentPomodoro));
                });
    }

    @Override
    public Observable<Long> startInterval() {
        return preferences.get(Single.just(DEFAULTS))
                .flatMapObservable(userSettings -> counter.start(userSettings.getNormalIntervalDuration()));
    }

    private void savePomodoroAction(Pomodoro currentPomodoro) {
        currentPomodoro.finish();
        dataSource.save(Single.just(currentPomodoro))
                .mergeWith(preferences.incrementPomodoro())
                .observeOn(Schedulers.io())
                .subscribe(Subscribers.empty());
    }
}
