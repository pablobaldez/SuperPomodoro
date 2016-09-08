package pablobaldez.github.superpomodoro.presentation.newpomodoro;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.domain.HandlePomodoroUseCase;
import pablobaldez.github.superpomodoro.domain.UserSettings;
import rx.Subscription;
import rx.functions.Action1;
import rx.observers.Subscribers;


/**
 * @author Pablo
 * @since 06/09/2016
 */
public class NewPomodoroPresenter {

    private static final UserSettings DEFAULTS = new UserSettings(
            TimeUnit.MINUTES.toMillis(25),
            TimeUnit.MINUTES.toMillis(5),
            TimeUnit.MINUTES.toMillis(15),
            4
    );

    private HandlePomodoroUseCase useCase;

    private NewPomodoroMvpView view;
    private Subscription subscription;

    @Inject
    public NewPomodoroPresenter(HandlePomodoroUseCase useCase) {
        this.useCase = useCase;
    }

    public void attachView(NewPomodoroMvpView view) {
        this.view = view;
    }

    public long getInitialPomodoroTime() {
        return DEFAULTS.getPomodoroDurationTime();
    }

    public void onReceiveIntervalConfirmation(boolean confirmed) {
        view.showRunningState();
        if(confirmed) {
            useCase.startInterval().subscribe(Subscribers.create(
                    view::setPomodoroTime,
                    throwable -> {}
            ));
        }

    }

    public void onRunClicked() {
        view.showRunningState();
        subscription = useCase.start().subscribe(Subscribers.create(
                view::setPomodoroTime,
                throwable -> {},
                () -> view.showFinishedState())
        );
    }

    public void onStopClicked() {
        if(subscription != null) {
            subscription.unsubscribe();
            view.showStoppedState();
        }
    }
}
