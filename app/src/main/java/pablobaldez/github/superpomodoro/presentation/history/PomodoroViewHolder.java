package pablobaldez.github.superpomodoro.presentation.history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.domain.Pomodoro;
import pablobaldez.github.superpomodoro.presentation.utils.TimeFormatUtils;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class PomodoroViewHolder extends RecyclerView.ViewHolder {

    private TextView workedTimeTextView;
    private TextView finishedTimeTextView;
    private TextView tookTimeTextView;

    public static PomodoroViewHolder newInstance(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_pomodoro, parent, false);
        return new PomodoroViewHolder(view);
    }

    public PomodoroViewHolder(View itemView) {
        super(itemView);
        workedTimeTextView = (TextView) itemView.findViewById(R.id.worked_time);
        finishedTimeTextView = (TextView) itemView.findViewById(R.id.finished);
        tookTimeTextView = (TextView) itemView.findViewById(R.id.took);
    }

    public void bind(Pomodoro pomodoro) {
        Context context = itemView.getContext();
        workedTimeTextView.setText(TimeFormatUtils.mmSS(pomodoro.getWorkedTime()));
        finishedTimeTextView.setText(pomodoro.isFinished()? R.string.finished : R.string.stopped);
        tookTimeTextView.setText(TimeFormatUtils.getHoursDiff(context, pomodoro.getTook()));
    }
}
