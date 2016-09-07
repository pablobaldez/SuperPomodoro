package pablobaldez.github.superpomodoro.presentation;


import android.app.Fragment;
import android.support.v13.app.FragmentPagerAdapter;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.presentation.newpomodoro.NewPomodoroFragment;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int[] TITLES = new int[]{R.string.new_, R.string.history};

    public static final int NEW_POMODORO_FRAGMENT_TAB = 0;

    private final MainActivity activity;

    public MainPagerAdapter(MainActivity activity) {
        super(activity.getFragmentManager());
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        return NEW_POMODORO_FRAGMENT_TAB == position ?  new NewPomodoroFragment() :
                new NewPomodoroFragment();
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return activity.getString(TITLES[position]);
    }
}
