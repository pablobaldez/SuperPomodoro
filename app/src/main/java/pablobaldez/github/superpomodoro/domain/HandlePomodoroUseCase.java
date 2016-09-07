package pablobaldez.github.superpomodoro.domain;


import java.util.concurrent.TimeUnit;

import pablobaldez.github.superpomodoro.data.PomodoroCounter;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import pablobaldez.github.superpomodoro.data.UserSettings;
import rx.Observable;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class HandlePomodoroUseCase {

    private static final UserSettings DEFAULTS = new UserSettings(
            TimeUnit.MINUTES.toMillis(25),
            TimeUnit.MINUTES.toMillis(5),
            TimeUnit.MINUTES.toMillis(15),
            4
    );


    private final PomodoroCounter counter;
    private final DataSource dataSource;

    public HandlePomodoroUseCase(PomodoroCounter counter, DataSource dataSource) {
        this.counter = counter;

        this.dataSource = dataSource;
    }

    public Observable<Long> runPomodoro(){
        return counter.start(DEFAULTS.getPomodoroDurationTime());
    }

}
