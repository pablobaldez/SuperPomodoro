package pablobaldez.github.superpomodoro.presentation;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.SuperPomodoroApplication;
import pablobaldez.github.superpomodoro.injection.AppComponent;
import pablobaldez.github.superpomodoro.injection.PresentationComponent;
import pablobaldez.github.superpomodoro.presentation.history.HistoryFragment;
import pablobaldez.github.superpomodoro.presentation.newpomodoro.NewPomodoroFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupTabs();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    private void setupTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
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
        return fragment;
    }

    private PresentationComponent buildPresentationComponent() {
        return getAppComponent().plus();
    }

    private AppComponent getAppComponent() {
        return SuperPomodoroApplication.getAppComponent(this);
    }


}
