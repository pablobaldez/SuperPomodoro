package pablobaldez.github.superpomodoro.presentation.newpomodoro;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.presentation.utils.TimeFormatUtils;

/**
 * @author Pablo
 * @since 06/09/2016
 */
public class NewPomodoroFragment extends Fragment implements NewPomodoroMvpView{

    private NewPomodoroPresenter presenter;

    private TextView timerTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewPomodoroPresenter(null);
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
        view.findViewById(R.id.play_stop_button).setOnClickListener(button -> presenter.onRunClicked());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewResumed();
    }

    @Override
    public void setPomodoroTime(long time) {
        timerTextView.setText(TimeFormatUtils.mmSS(time));
    }

    @Override
    public void showRunningState() {

    }

    @Override
    public void showWaitingState() {

    }
}