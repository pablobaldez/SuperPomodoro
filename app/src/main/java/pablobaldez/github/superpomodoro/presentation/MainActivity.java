package pablobaldez.github.superpomodoro.presentation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.presentation.history.HistoryFragment;
import pablobaldez.github.superpomodoro.presentation.newpomodoro.NewPomodoroFragment;
import pablobaldez.github.superpomodoro.presentation.settings.SettingsActivity;
import pablobaldez.github.superpomodoro.presentation.utils.BaseActivity;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public Fragment createNewPomodoroFragment() {
        NewPomodoroFragment fragment = new NewPomodoroFragment();
        buildPresentationComponent().inject(fragment);
        return fragment;
    }

    public Fragment createHistoryFragment() {
        HistoryFragment fragment = new HistoryFragment();
        buildPresentationComponent().inject(fragment);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position != MainPagerAdapter.NEW_POMODORO_FRAGMENT_TAB) {
                    fragment.onSelect();
                }
            }
        });
        return fragment;
    }

}
