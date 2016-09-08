package pablobaldez.github.superpomodoro.presentation.settings;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.domain.UserSettings;
import pablobaldez.github.superpomodoro.domain.workers.Preferences;
import pablobaldez.github.superpomodoro.presentation.utils.BackgroundTransformer;
import rx.Completable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;


/**
 * @author Pablo
 * @since 08/09/2016
 */
public class SettingsPresenter {

    private final Preferences<UserSettings> preferences;

    private UserSettings settings;
    private SettingsMvpView view;

    @Inject
    public SettingsPresenter(Preferences<UserSettings> preferences) {
        this.preferences = preferences;
    }

    public void attachView(SettingsMvpView view) {
        this.view = view;
        preferences.get(Single.just(UserSettings.DEFAULTS))
                .compose(new BackgroundTransformer<UserSettings>().toSingle())
                .subscribe(new SingleSubscriber<UserSettings>() {
                    @Override
                    public void onSuccess(UserSettings value) {
                        settings = value;
                        init();
                    }

                    @Override
                    public void onError(Throwable error) {
                        view.showDefaultErrorMessage();
                    }
                });

    }

     void init() {
        int pomodoroDuration = (int) TimeUnit.MILLISECONDS.toMinutes(settings.getPomodoroDurationTime());
        int intervalTime = (int) TimeUnit.MILLISECONDS.toMinutes(settings.getNormalIntervalDuration());
        view.initForm(pomodoroDuration, intervalTime);
    }

    public void save(String pomodoroDurationStr, String intervalTimeStr) {
        int pomodoroDuration = toNumber(pomodoroDurationStr);
        int intervalTime = toNumber(intervalTimeStr);
        boolean noError = true;

        if(pomodoroDuration == 0) {
            noError = false;
            view.showEmptyPomodoroDuration();
        }
        else if(pomodoroDuration == -1) {
            noError = false;
            view.showInvalidPomodoroDuration();
        }
        if(intervalTime == 0) {
            noError = false;
            view.showEmptyIntervalTime();
        }
        else if(intervalTime == -1) {
            noError = false;
            view.showInvalidIntervalTime();
        }

        if(noError){
            save(pomodoroDuration, intervalTime);
        }

    }

    private int toNumber(String text) {
        if(text.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void save(int pomodoroDuration, int intervalTime) {
        settings.setPomodoroDurationTime(TimeUnit.MINUTES.toMillis(pomodoroDuration));
        settings.setNormalIntervalDuration(TimeUnit.MINUTES.toMillis(intervalTime));
        preferences.save(Single.just(settings))
                .compose(new BackgroundTransformer<UserSettings>().toCompletable())
                .subscribe(new Completable.CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        view.saved();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showDefaultErrorMessage();
                    }

                    @Override
                    public void onSubscribe(Subscription d) {

                    }
                });
    }

}
