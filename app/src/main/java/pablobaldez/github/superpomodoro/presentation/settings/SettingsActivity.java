package pablobaldez.github.superpomodoro.presentation.settings;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import javax.inject.Inject;

import pablobaldez.github.superpomodoro.R;
import pablobaldez.github.superpomodoro.presentation.utils.BaseActivity;
import pablobaldez.github.superpomodoro.presentation.widgets.CustomTextInput;

/**
 * @author Pablo
 * @since 08/09/2016
 */
public class SettingsActivity extends BaseActivity implements SettingsMvpView{

    private SettingsPresenter presenter;

    private EditText pomodoroDurationEditText;
    private CustomTextInput intervalTimeEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        buildPresentationComponent().inject(this);
        pomodoroDurationEditText = (EditText) findViewById(R.id.pomodoro_time_edit_text);
        intervalTimeEditText = (CustomTextInput) findViewById(R.id.interval_time_edit_text);
        intervalTimeEditText.setOnActionDoneClickListener(view ->
                presenter.save(pomodoroDurationEditText.getText().toString(), intervalTimeEditText.getText().toString())
        );
        presenter.attachView(this);
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                presenter.save(pomodoroDurationEditText.getText().toString(),
                        intervalTimeEditText.getText().toString());
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Inject
    public void setPresenter(SettingsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showDefaultErrorMessage() {
        Snackbar.make(intervalTimeEditText, R.string.default_error_message, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void initForm(int pomodoroDuration, int intervalTime) {
        Locale locale = Locale.getDefault();
        String text = String.format(locale, "%02d", pomodoroDuration);
        pomodoroDurationEditText.setText(text);
        text = String.format(locale, "%02d", intervalTime);
        intervalTimeEditText.setText(text);
    }

    @Override
    public void showInvalidPomodoroDuration() {
        pomodoroDurationEditText.setError(getString(R.string.invalid));
    }

    @Override
    public void showEmptyPomodoroDuration() {
        pomodoroDurationEditText.setError(getString(R.string.required));
    }

    @Override
    public void showInvalidIntervalTime() {
        intervalTimeEditText.setError(getString(R.string.invalid));
    }

    @Override
    public void showEmptyIntervalTime() {
        intervalTimeEditText.setError(getString(R.string.required));
    }

    @Override
    public void saved() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        finish();
    }
}
