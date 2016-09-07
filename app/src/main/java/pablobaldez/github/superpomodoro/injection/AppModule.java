package pablobaldez.github.superpomodoro.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pablobaldez.github.superpomodoro.data.ObservableCountDown;
import pablobaldez.github.superpomodoro.data.RealmPomodoroDataSource;
import pablobaldez.github.superpomodoro.data.UserPreferences;
import pablobaldez.github.superpomodoro.domain.HandlePomodoroUseCase;
import pablobaldez.github.superpomodoro.domain.HandlePomodoroUseCaseImpl;
import pablobaldez.github.superpomodoro.domain.Pomodoro;
import pablobaldez.github.superpomodoro.domain.UserSettings;
import pablobaldez.github.superpomodoro.domain.workers.DataSource;
import pablobaldez.github.superpomodoro.domain.workers.Preferences;

/**
 * @author Pablo
 * @since 07/09/2016
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public DataSource<Pomodoro> providePomodoroDataSource(RealmPomodoroDataSource dataSource){
        return dataSource;
    }

    @Provides
    public ObservableCountDown provideObservableCountDown() {
        return new ObservableCountDown();
    }

    @Provides
    public Preferences<UserSettings> provideUserSettingsPreferences(UserPreferences preferences) {
        return preferences;
    }

    @Provides
    public HandlePomodoroUseCase handlePomodoroUseCase(HandlePomodoroUseCaseImpl useCase) {
        return useCase;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

}
