package pablobaldez.github.superpomodoro;

import android.app.Activity;
import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import pablobaldez.github.superpomodoro.injection.AppComponent;
import pablobaldez.github.superpomodoro.injection.AppModule;
import pablobaldez.github.superpomodoro.injection.DaggerAppComponent;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class SuperPomodoroApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new MaterialModule());
        initRealm();
        initAppComponent();
    }

    private void initRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
    }

    public AppComponent getAppComponent() {
        if(appComponent == null) {
            initAppComponent();
        }
        return appComponent;
    }

    public static AppComponent getAppComponent(Activity activity) {
        SuperPomodoroApplication application = (SuperPomodoroApplication) activity.getApplication();
        return application.getAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }
}
