package pablobaldez.github.superpomodoro.presentation.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.presentation.utils.TimeFormatUtils;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class DayViewHolder extends RecyclerView.ViewHolder {

    private TextView dayTextView;

    public static DayViewHolder newInstance(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_day, parent, false);
        return new DayViewHolder(view);
    }

    public DayViewHolder(View itemView) {
        super(itemView);
        dayTextView = (TextView) itemView.findViewById(R.id.day);
    }

    public void bind(Date day) {
        String text = TimeFormatUtils.getDaysDiff(dayTextView.getContext(), day);
        dayTextView.setText(text);
    }
}
