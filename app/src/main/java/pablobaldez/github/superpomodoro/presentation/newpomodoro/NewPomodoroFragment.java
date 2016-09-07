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
    private PlayFloatingActionButton playButton;
    private Animation blinkAnimation;

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
        blinkAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        playButton = (PlayFloatingActionButton) view.findViewById(R.id.play_stop_button);
    }

    @Override
    public void onResume() {
        super.onResume();
        timerTextView.setText(TimeFormatUtils.mmSS(presenter.getInitialPomodoroTime()));
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
        timerTextView.setText(TimeFormatUtils.mmSS(time));
    }

    @Override
    public void showRunningState() {
        timerTextView.setEnabled(true);
    }

    @Override
    public void showFinishedState() {
        timerTextView.setText(TimeFormatUtils.mmSS(0));
        timerTextView.setEnabled(false);
        timerTextView.startAnimation(blinkAnimation);
    }

    @Override
    public void showStoppedState() {
        timerTextView.setEnabled(false);
        playButton.toggle();
        timerTextView.startAnimation(blinkAnimation);
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
        timerTextView.setText(TimeFormatUtils.mmSS(presenter.getInitialPomodoroTime()));
        playButton.setEnabled(true);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
