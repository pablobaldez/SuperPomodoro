package pablobaldez.github.superpomodoro.presentation.newpomodoro;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.domain.HandlePomodoroUseCase;
import pablobaldez.github.superpomodoro.domain.UserSettings;
import pablobaldez.github.superpomodoro.domain.workers.Preferences;
import pablobaldez.github.superpomodoro.presentation.utils.BackgroundTransformer;
import rx.Single;
import rx.Subscription;
import rx.observers.Subscribers;

import static pablobaldez.github.superpomodoro.domain.UserSettings.DEFAULTS;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class NewPomodoroPresenter {

    private final HandlePomodoroUseCase useCase;
    private final Preferences<UserSettings> preferences;

    private NewPomodoroMvpView view;
    private Subscription subscription;

    private boolean isInInterval = false;
    private UserSettings settings;

    @Inject
    public NewPomodoroPresenter(HandlePomodoroUseCase useCase, Preferences<UserSettings> preferences) {
        this.useCase = useCase;
        this.preferences = preferences;
    }

    public void attachView(NewPomodoroMvpView view) {
        this.view = view;
    }

    public void onViewResumed(){
        preferences.get(Single.just(DEFAULTS))
                .compose(new BackgroundTransformer<UserSettings>().toSingle())
                .subscribe(userSettings -> {
                    settings = userSettings;
                    view.setCurrentPomodoroDuration(userSettings.getPomodoroDurationTime());
                });
    }

    public void onReceiveIntervalConfirmation(boolean confirmed) {
        isInInterval = true;
        if(confirmed) {
            view.showIntervalTime();
            view.setPomodoroTime(settings.getNormalIntervalDuration());
            view.showRunningState();
            useCase.startInterval().subscribe(Subscribers.create(
                    view::setPomodoroTime,
                    throwable -> {},
                    this::onCompleteInterval
            ));
        }
        else {
            view.showFinishedState();
        }
    }

    private void onCompleteInterval() {
        isInInterval = false;
        view.showFinishedState();
    }

    public void onRunClicked() {
        isInInterval = false;
        view.showRunningState();
        subscription = useCase.start().subscribe(Subscribers.create(
                view::setPomodoroTime,
                throwable -> {},
                () -> view.askForInterval())
        );
    }

    public void onStopClicked() {
        if(subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            view.showStoppedState();
        }
        else if(isInInterval) {
            view.showStoppedState();
        }
    }
}
