package pablobaldez.github.superpomodoro.presentation.newpomodoro;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.domain.HandlePomodoroUseCase;
import pablobaldez.github.superpomodoro.domain.UserSettings;
import rx.functions.Action0;
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

    @Inject
    public NewPomodoroPresenter(HandlePomodoroUseCase useCase) {
        this.useCase = useCase;
    }

    public void attachView(NewPomodoroMvpView view) {
        this.view = view;
    }

    public void onViewResumed() {
        view.setPomodoroTime(DEFAULTS.getPomodoroDurationTime());
    }

    public void onRunClicked() {
        useCase.start()
                .subscribe(time -> view.setPomodoroTime(time));
    }

    public void onStopClicked() {
        useCase.stop().subscribe(new Action0() {
            @Override
            public void call() {
                view.showWaitingState();
            }
        });
    }
}
