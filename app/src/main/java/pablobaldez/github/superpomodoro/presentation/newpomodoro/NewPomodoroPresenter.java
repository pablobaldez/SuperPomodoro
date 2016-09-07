package pablobaldez.github.superpomodoro.presentation.newpomodoro;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.domain.HandlePomodoroUseCase;
import pablobaldez.github.superpomodoro.domain.UserSettings;
import rx.Subscriber;
import rx.Subscription;

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

    public void onRunClicked() {
        subscription = useCase.start().subscribe(new Subscriber<Long>() {
            @Override
            public void onStart() {
                super.onStart();
                view.showRunningState();
            }

            @Override
            public void onCompleted() {
                view.showFinishedState();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("SuperPomodoro", "error on start pomodoro", e);
            }

            @Override
            public void onNext(Long time) {
                view.setPomodoroTime(time);
            }
        });
    }

    public void onStopClicked() {
        if(subscription != null) {
            subscription.unsubscribe();
            view.showStoppedState();
        }
    }
}
