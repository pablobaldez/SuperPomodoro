package pablobaldez.github.superpomodoro.presentation.newpomodoro;

import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;

import pablobaldez.github.superpomodoro.domain.workers.DataSource;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class NewPomodoroPresenter {

    private static final long POMODORO_DURATION = TimeUnit.MINUTES.toMillis(25);

    private final DataSource dataSource;

    private NewPomodoroMvpView view;

    public NewPomodoroPresenter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void attachView(NewPomodoroMvpView view) {
        this.view = view;
    }

    public void onViewResumed() {
        view.setPomodoroTime(POMODORO_DURATION);
    }

    public void onRunClicked() {
        new CountDownTimer(POMODORO_DURATION, 1000) {
            @Override
            public void onTick(long time) {
                view.setPomodoroTime(time);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void onStopClicked() {

    }
}
