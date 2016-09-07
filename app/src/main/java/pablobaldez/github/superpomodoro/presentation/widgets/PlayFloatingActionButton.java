package pablobaldez.github.superpomodoro.presentation.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;

import pablobaldez.github.superpomodoro.R;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class PlayFloatingActionButton extends FloatingActionButton {

    private Drawable playDrawable;
    private Drawable stopDrawable;

    public PlayFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public PlayFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlayFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        super.setOnClickListener(view -> {
            toggle();
            listener.onClick(view);
        });
    }

    private void init() {
        initDrawable();
        setOnClickListener(view -> {});
    }

    private void initDrawable() {
        int white = android.R.color.white;
        Context context = getContext();
        playDrawable = new IconDrawable(context, MaterialIcons.md_play_arrow).colorRes(white);
        stopDrawable = new IconDrawable(context, MaterialIcons.md_stop).colorRes(white);
        setImageResource(R.drawable.ic_vector_play_arrow);
    }

    public void toggle() {
        setPlaying(!isPlaying());
        setImageDrawable(isPlaying() ? stopDrawable : playDrawable);
    }

    public void setPlaying(boolean playing) {
        setSelected(playing);
    }

    public boolean isPlaying() {
        return isSelected();
    }

}
