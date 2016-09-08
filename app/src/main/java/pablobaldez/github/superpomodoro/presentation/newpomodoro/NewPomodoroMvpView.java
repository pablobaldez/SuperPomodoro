package pablobaldez.github.superpomodoro.presentation.newpomodoro;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public interface NewPomodoroMvpView {

    void setPomodoroTime(long time);

    void showIntervalTime();

    void showRunningState();

    void showFinishedState();

    void showStoppedState();

    void askForInterval();

    void setCurrentPomodoroDuration(long currentPomodoroDuration);

}
