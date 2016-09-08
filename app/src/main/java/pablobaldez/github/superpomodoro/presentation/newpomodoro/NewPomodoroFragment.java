package pablobaldez.github.superpomodoro.presentation.newpomodoro;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.presentation.utils.TimeFormatUtils;
import pablobaldez.github.superpomodoro.presentation.widgets.PlayFloatingActionButton;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class NewPomodoroFragment extends Fragment implements NewPomodoroMvpView, Animation.AnimationListener {

    private NewPomodoroPresenter presenter;

    private TextView timerTextView;
    private TextView intervalTextView;
    private PlayFloatingActionButton playButton;
    private Animation blinkAnimation;
    private Animation intervalAnimation;

    private long currentPomodoroDuration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_pomodoro, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timerTextView = (TextView) view.findViewById(R.id.timer);
        intervalTextView = (TextView) view.findViewById(R.id.interval_time);
        blinkAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        intervalAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        playButton = (PlayFloatingActionButton) view.findViewById(R.id.play_stop_button);
    }

    @Override
    public void onResume() {
        super.onResume();
        playButton.setEnabled(false);
        presenter.onViewResumed();
        blinkAnimation.setAnimationListener(this);
        playButton.setOnClickListener(v -> {
            if(playButton.isPlaying()) {
                presenter.onRunClicked();
            }
            else {
                presenter.onStopClicked();
            }
        });
    }

    @Override
    public void setPomodoroTime(long time) {
        if(time == 0){
            animate();
        }
        timerTextView.setText(TimeFormatUtils.mmSS(time));
    }

    @Override
    public void showIntervalTime() {
        intervalTextView.setVisibility(View.VISIBLE);
        intervalTextView.startAnimation(intervalAnimation);
    }

    @Override
    public void showRunningState() {
        timerTextView.setEnabled(true);
    }

    @Override
    public void showFinishedState() {
        intervalTextView.setVisibility(View.GONE);
        timerTextView.setText(TimeFormatUtils.mmSS(0));
        playButton.toggle();
        animate();
    }

    @Override
    public void showStoppedState() {
        intervalTextView.setVisibility(View.GONE);
        animate();
    }

    private synchronized void animate(){
        timerTextView.setEnabled(false);
        timerTextView.startAnimation(blinkAnimation);
    }

    @Override
    public void askForInterval() {
        new MaterialDialog.Builder(getActivity())
                .content(R.string.message_interval_confirmation)
                .positiveText(android.R.string.yes)
                .onPositive((dialog, which) -> presenter.onReceiveIntervalConfirmation(true))
                .onNegative((dialog, which) -> presenter.onReceiveIntervalConfirmation(false))
                .negativeText(android.R.string.no)
                .show();
    }

    @Override
    public void setCurrentPomodoroDuration(long currentPomodoroDuration) {
        playButton.setEnabled(true);
        this.currentPomodoroDuration = currentPomodoroDuration;
        timerTextView.setText(TimeFormatUtils.mmSS(currentPomodoroDuration));
    }

    @Inject
    public void setPresenter(NewPomodoroPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        playButton.setEnabled(false);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        timerTextView.setText(TimeFormatUtils.mmSS(currentPomodoroDuration));
        playButton.setEnabled(true);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
