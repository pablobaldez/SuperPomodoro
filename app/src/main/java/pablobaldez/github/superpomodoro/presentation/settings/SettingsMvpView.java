package pablobaldez.github.superpomodoro.presentation.settings;

/**
 * @author Pablo
 * @since 08/09/2016
 */
public interface SettingsMvpView {

    void showDefaultErrorMessage();

    void initForm(int pomodoroDuration, int intervalTime);

    void showInvalidPomodoroDuration();

    void showEmptyPomodoroDuration();

    void showInvalidIntervalTime();

    void showEmptyIntervalTime();

    void saved();

}
