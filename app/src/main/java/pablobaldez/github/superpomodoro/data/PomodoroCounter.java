package pablobaldez.github.superpomodoro.data;

import android.os.CountDownTimer;

import rx.Observable;
import rx.subscriptions.Subscriptions;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class PomodoroCounter {


    public PomodoroCounter() {}

    public Observable<Long> start(long time) {
        return Observable.create(subscriber -> {
            CountDownTimer countDownTimer = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long tick) {
                    subscriber.onNext(tick);
                }

                @Override
                public void onFinish() {
                    subscriber.onCompleted();
                }
            };
            subscriber.add(Subscriptions.create(countDownTimer::cancel));
        });
    }
}
