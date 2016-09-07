package pablobaldez.github.superpomodoro;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Pablo
 * @since 07/09/2016
 */
public class SuperPomodoroApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new MaterialModule());
        initRealm();
    }

    private void initRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
    }
}
