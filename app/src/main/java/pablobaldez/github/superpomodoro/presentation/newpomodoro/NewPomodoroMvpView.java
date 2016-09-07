package pablobaldez.github.superpomodoro.presentation.newpomodoro;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public interface NewPomodoroMvpView {

    void setPomodoroTime(long time);

    void showRunningState();

    void showWaitingState();

}
