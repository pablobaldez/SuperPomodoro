package pablobaldez.github.superpomodoro.presentation.utils;

import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.SuperPomodoroApplication;
import pablobaldez.github.superpomodoro.injection.AppComponent;
import pablobaldez.github.superpomodoro.injection.PresentationComponent;

/**
 * @author Pablo
 * @since 08/09/2016
 */
public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar();
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    protected PresentationComponent buildPresentationComponent() {
        return getAppComponent().plus();
    }

    protected AppComponent getAppComponent() {
        return SuperPomodoroApplication.getAppComponent(this);
    }
}
